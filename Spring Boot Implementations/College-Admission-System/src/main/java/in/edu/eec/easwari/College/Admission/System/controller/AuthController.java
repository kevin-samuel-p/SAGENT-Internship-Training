package in.edu.eec.easwari.College.Admission.System.controller;

import in.edu.eec.easwari.College.Admission.System.dto.UserDto;
import in.edu.eec.easwari.College.Admission.System.entity.User;
import in.edu.eec.easwari.College.Admission.System.repository.UserRepository;
import in.edu.eec.easwari.College.Admission.System.utils.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    // Constructor Injection... (omitted for brevity)
    public AuthController(UserRepository userRepo, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDto user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encoder.encode(user.getPassword()));
        // Default to student if not specified
        newUser.setRole("ROLE_STUDENT");
        userRepo.save(newUser);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto loginRequest) {
        Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && encoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return jwtUtils.generateToken(user.get().getEmail());
        }
        throw new RuntimeException("Invalid credentials");
    }
}