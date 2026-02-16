package in.edu.eec.easwari.Personal.Budget.Tracker.App.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.UserLoginRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.UserRegistrationRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.UserLoginResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.UserRegistrationResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.entity.User;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.repository.UserRepository;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public UserRegistrationResponseDTO register(@RequestBody UserRegistrationRequestDTO req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
        return new UserRegistrationResponseDTO(
                    user.getUserId(), 
                    user.getName(), 
                    user.getEmail(), 
                    user.getPassword());
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginRequestDTO req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        String token = jwtUtil.generateToken(req.getEmail());
        return new UserLoginResponseDTO(token, "Bearer");
    }
}
