package in.edu.eec.easwari.Personal.Budget.Tracker.App.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.DashboardSummaryDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.MonthlyFinanceDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserExpenseRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserIncomeRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.DashboardService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserIncomeRepository incomeRepository;
    private final UserExpenseRepository expenseRepository;

    @Override
    public DashboardSummaryDTO getDashboardSummary(Long userId) {

        BigDecimal totalIncome = incomeRepository.getTotalIncome(userId);
        BigDecimal totalExpenses = expenseRepository.getTotalExpensesByUser(userId);

        BigDecimal balance = totalIncome.subtract(totalExpenses);

        return DashboardSummaryDTO.builder()
                .totalIncome(totalIncome)
                .totalExpenses(totalExpenses)
                .balance(balance)
                .build();
    }

    @Override
    public List<MonthlyFinanceDTO> getMonthlyAnalytics(Long userId) {

        Map<Integer, MonthlyFinanceDTO> map =
                incomeRepository.getMonthlyIncome(userId)
                        .stream()
                        .collect(Collectors.toMap(
                                MonthlyFinanceDTO::getMonth,
                                dto -> new MonthlyFinanceDTO(
                                        dto.getMonth(),
                                        dto.getTotalIncome(),
                                        BigDecimal.ZERO)));

        for (MonthlyFinanceDTO exp :
                expenseRepository.getMonthlyExpenses(userId)) {

            map.merge(
                exp.getMonth(),
                new MonthlyFinanceDTO(
                        exp.getMonth(),
                        BigDecimal.ZERO,
                        exp.getTotalExpense()),
                (existing, incoming) -> {
                    existing.setTotalExpense(
                        existing.getTotalExpense()
                            .add(incoming.getTotalExpense()));
                    return existing;
                });
        }

        return map.values()
                  .stream()
                  .sorted(Comparator.comparing(MonthlyFinanceDTO::getMonth))
                  .toList();
    }
}
