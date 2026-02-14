package in.edu.eec.easwari.Library.Management.System.dto;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String name;
    private String email;
    private String password;
}
