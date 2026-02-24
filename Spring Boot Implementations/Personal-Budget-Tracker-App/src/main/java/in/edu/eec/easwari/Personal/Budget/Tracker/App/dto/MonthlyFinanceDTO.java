package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyFinanceDTO {
    private Integer month;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
}