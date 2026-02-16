package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseRequestDTO {

    private Long userId;
    private Long categoryId;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String description;
}
