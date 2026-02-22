package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetUsageDTO {

    private String categoryName;
    private BigDecimal monthlyLimit;
    private BigDecimal totalSpent;
    private BigDecimal percentageUsed;
}