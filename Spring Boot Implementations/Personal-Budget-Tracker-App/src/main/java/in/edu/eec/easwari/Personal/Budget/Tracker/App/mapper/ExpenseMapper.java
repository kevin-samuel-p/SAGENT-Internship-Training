package in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.ExpenseResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserExpense;

public class ExpenseMapper {

    public static ExpenseResponseDTO toDTO(UserExpense expense) {
        return ExpenseResponseDTO.builder()
                .expenseId(expense.getExpenseId())
                .categoryId(expense.getCategory().getCategoryId())
                .amount(expense.getAmount())
                .expenseDate(expense.getExpenseDate())
                .description(expense.getDescription())
                .build();
    }
}
