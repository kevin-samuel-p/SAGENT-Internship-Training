package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
}
