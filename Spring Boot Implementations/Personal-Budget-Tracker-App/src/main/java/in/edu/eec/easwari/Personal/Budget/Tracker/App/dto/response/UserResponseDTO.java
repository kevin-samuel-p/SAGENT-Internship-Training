package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private Long userId;
    private String name;
    private String email;
}