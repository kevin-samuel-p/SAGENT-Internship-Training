package in.edu.eec.easwari.Personal.Budget.Tracker.App.service;

import java.math.BigDecimal;
import java.util.List;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.ExpenseCategoryDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.ExpenseRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.ExpenseResponseDTO;

public interface ExpenseService {

    ExpenseResponseDTO addExpense(ExpenseRequestDTO request);
    void removeExpense(Long id);

    List<ExpenseResponseDTO> getUserExpenses(Long userId);

    BigDecimal getTotalExpenses(Long userId);

    List<ExpenseCategoryDTO> getAllCategories();
}
