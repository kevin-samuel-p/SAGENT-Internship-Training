package in.edu.eec.easwari.Personal.Budget.Tracker.App.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.request.UserRegistrationRequestDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.dto.response.UserResponseDTO;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.service.UserService;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.exception.ApiResponse;
import in.edu.eec.easwari.Personal.Budget.Tracker.App.util.ResponseUtil;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(
            @RequestBody UserRegistrationRequestDTO request) {

        UserResponseDTO response = userService.registerUser(request);

        return ResponseEntity.ok(
                ResponseUtil.success(response, "User registered successfully")
        );
    }
}
