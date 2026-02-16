package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BudgetRequestDTO {

    private Long userId;
    private Long categoryId;
    private BigDecimal monthlyLimit;
    private Integer month;
    private Integer year;
}
