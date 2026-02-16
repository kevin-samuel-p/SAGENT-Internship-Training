package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResponseDTO {
    private Long userId;
    private String name;
    private String email;
    private String message;
}
