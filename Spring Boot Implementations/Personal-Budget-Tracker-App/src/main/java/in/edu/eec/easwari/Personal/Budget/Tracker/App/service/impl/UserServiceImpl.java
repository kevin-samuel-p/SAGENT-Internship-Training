package in.edu.eec.easwari.Personal.Budget.Tracker.App.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.UserRegistrationRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.UserResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.User;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.DuplicateResourceException;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.mapper.UserMapper;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO registerUser(UserRegistrationRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered.");
        }

        User user = UserMapper.toEntity(request);

        // ⚠ Later: Hash password with BCrypt
        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser);
    }
}
