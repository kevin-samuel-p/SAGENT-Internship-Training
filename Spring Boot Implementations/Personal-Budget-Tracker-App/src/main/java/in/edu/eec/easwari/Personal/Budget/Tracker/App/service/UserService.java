package in.edu.eec.easwari.Personal.Budget.Tracker.App.service;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.UserRegistrationRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(UserRegistrationRequestDTO request);
}
