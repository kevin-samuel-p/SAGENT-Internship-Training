package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class IncomeResponseDTO {

    private Long incomeId;
    private String sourceName;
    private BigDecimal amount;
    private LocalDate incomeDate;
}
