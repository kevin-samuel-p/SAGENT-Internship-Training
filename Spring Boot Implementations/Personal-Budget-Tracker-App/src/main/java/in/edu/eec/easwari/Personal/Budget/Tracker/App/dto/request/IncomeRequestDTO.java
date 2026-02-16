package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IncomeRequestDTO {

    private Long userId;
    private Long sourceId;
    private BigDecimal amount;
    private LocalDate incomeDate;
}