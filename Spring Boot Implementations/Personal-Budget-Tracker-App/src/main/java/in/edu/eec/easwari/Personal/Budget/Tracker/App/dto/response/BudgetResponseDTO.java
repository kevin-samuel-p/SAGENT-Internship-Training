package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BudgetResponseDTO {

    private Long budgetId;
    private String categoryName;
    private BigDecimal monthlyLimit;
    private Integer month;
    private Integer year;
}
