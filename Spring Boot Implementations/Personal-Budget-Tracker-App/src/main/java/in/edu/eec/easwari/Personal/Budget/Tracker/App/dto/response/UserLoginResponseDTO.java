package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDTO {
    private String token;
    private String tokenType = "Bearer";
}
