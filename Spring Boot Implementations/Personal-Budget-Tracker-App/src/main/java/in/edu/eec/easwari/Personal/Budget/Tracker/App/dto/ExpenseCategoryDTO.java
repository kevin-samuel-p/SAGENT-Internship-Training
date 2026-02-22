package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseCategoryDTO {
    private Long id;
    private String category;
    private String description;
}
