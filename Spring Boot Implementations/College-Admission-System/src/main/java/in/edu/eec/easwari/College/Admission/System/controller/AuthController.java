package in.edu.eec.easwari.College.Admission.System.controller;

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
    public String register(@RequestBody in.edu.eec.easwari.College.Admission.System.entity.User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        // Default to student if not specified
        if(user.getRole() == null) user.setRole("ROLE_STUDENT");
        userRepo.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Optional<in.edu.eec.easwari.College.Admission.System.entity.User> user = userRepo.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && encoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return jwtUtils.generateToken(user.get().getEmail());
        }
        throw new RuntimeException("Invalid credentials");
    }
}