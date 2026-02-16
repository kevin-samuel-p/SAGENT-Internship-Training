package in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.UserRegistrationRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.UserResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.User;

public class UserMapper {

    public static User toEntity(UserRegistrationRequestDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
