package in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.BudgetResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.UserBudget;

public class BudgetMapper {

    public static BudgetResponseDTO toDTO(UserBudget budget) {
        return BudgetResponseDTO.builder()
                .budgetId(budget.getBudgetId())
                .categoryName(budget.getCategory().getCategoryName())
                .monthlyLimit(budget.getMonthlyLimit())
                .month(budget.getMonth())
                .year(budget.getYear())
                .build();
    }
}
