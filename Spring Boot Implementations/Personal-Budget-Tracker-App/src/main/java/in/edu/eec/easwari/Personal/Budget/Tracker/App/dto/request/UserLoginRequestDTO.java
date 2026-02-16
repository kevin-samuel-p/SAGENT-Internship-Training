package in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
