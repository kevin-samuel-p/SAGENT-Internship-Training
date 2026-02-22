package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ExpenseResponseDTO {

    private Long expenseId;
    private Long categoryId;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String description;
}
