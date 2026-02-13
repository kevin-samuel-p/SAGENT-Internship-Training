package in.edu.eec.easwari.Library.Management.System.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Library.Management.System.dto.UserCreationRequest;
import in.edu.eec.easwari.Library.Management.System.entity.User;
import in.edu.eec.easwari.Library.Management.System.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * Register User
     * POST /api/users/register
     */
    @PostMapping("/register")
    public User registerUser(@RequestBody UserCreationRequest request) {
        return userService.registerUser(
                            request.getName(), 
                            request.getEmail(), 
                            request.getPassword());
    }

    /*
     * Get User by ID
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /*
     * Get all users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
