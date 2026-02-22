package in.edu.eec.easwari.Personal.Budget.Tracker.App.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.BudgetUsageDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.BudgetRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.BudgetResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.ExpenseCategory;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.User;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserBudget;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper.BudgetMapper;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.ExpenseCategoryRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserExpenseRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserBudgetRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.BudgetService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetServiceImpl implements BudgetService {

    private final UserBudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final UserExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository categoryRepository;

    @Override
    public BudgetResponseDTO setBudget(BudgetRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ExpenseCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        UserBudget budget = budgetRepository
                .findByUser_UserIdAndCategory_CategoryIdAndMonthAndYear(
                        request.getUserId(),
                        request.getCategoryId(),
                        request.getMonth(),
                        request.getYear()
                )
                .orElse(UserBudget.builder()
                        .user(user)
                        .category(category)
                        .month(request.getMonth())
                        .year(request.getYear())
                        .build());

        budget.setMonthlyLimit(request.getMonthlyLimit());

        UserBudget saved = budgetRepository.save(budget);

        return BudgetMapper.toDTO(saved);
    }

    @Override
    public List<BudgetResponseDTO> getMonthlyBudgets(Long userId, Integer month, Integer year) {

        return budgetRepository.findByUser_UserIdAndMonthAndYear(userId, month, year)
                .stream()
                .map(BudgetMapper::toDTO)
                .toList();
    }

    @Override
    public List<BudgetUsageDTO> getBudgetUsage(Long userId) {
        return expenseRepository.getBudgetUsage(userId);
    }
}
