package in.edu.eec.easwari.Personal.Budget.Tracker.App.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.ExpenseCategoryDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.ExpenseRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.ExpenseResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.ExpenseCategory;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.User;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserExpense;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper.ExpenseMapper;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.ExpenseCategoryRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserExpenseRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.ExpenseService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final UserExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseCategoryRepository categoryRepository;

    @Override
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ExpenseCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        UserExpense expense = UserExpense.builder()
                .user(user)
                .category(category)
                .amount(request.getAmount())
                .expenseDate(request.getExpenseDate())
                .description(request.getDescription())
                .build();

        UserExpense saved = expenseRepository.save(expense);

        return ExpenseMapper.toDTO(saved);
    }

    @Override
    public void removeExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<ExpenseResponseDTO> getUserExpenses(Long userId) {
        return expenseRepository.findByUser_UserId(userId)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    @Override
    public BigDecimal getTotalExpenses(Long userId) {
        return expenseRepository.getTotalExpensesByUser(userId);
    }

    @Override
    public List<ExpenseCategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                    .stream()
                    .map(category -> new ExpenseCategoryDTO(
                            category.getCategoryId(), 
                            category.getCategoryName(), 
                            category.getCategoryDescription()))
                    .toList();
    }
}
