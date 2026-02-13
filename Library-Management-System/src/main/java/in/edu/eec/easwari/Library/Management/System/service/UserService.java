package in.edu.eec.easwari.Library.Management.System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Library.Management.System.entity.User;
import in.edu.eec.easwari.Library.Management.System.enums.UserRole;
import in.edu.eec.easwari.Library.Management.System.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email, String password) {

        if (userRepository.findByUserEmail(email).isPresent())
            throw new RuntimeException("Email already registered!");

        User user = new User();
        user.setUserName(name);
        user.setUserEmail(email);
        user.setUserPassword(password);

        // Member role automatically assigned
        user.setUserRole(UserRole.MEMBER);

        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
