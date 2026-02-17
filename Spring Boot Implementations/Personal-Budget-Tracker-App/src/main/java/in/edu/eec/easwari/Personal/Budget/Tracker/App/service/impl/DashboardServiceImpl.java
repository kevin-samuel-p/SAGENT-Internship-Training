package in.edu.eec.easwari.Personal.Budget.Tracker.App.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.DashboardSummaryDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserExpenseRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserIncomeRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.DashboardService;

import java.math.BigDecimal;

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
}
