package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeSourceDTO {
    private Long id;
    private String source;
    private String description;
}
