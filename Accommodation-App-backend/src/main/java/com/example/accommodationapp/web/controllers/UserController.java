package com.example.accommodationapp.web.controllers;

import com.example.accommodationapp.model.enumerations.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import com.example.accommodationapp.model.domain.User;
import com.example.accommodationapp.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or passwords do not match")
    })
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user, @RequestParam String repeatPassword) {
        try {
            User registeredUser = userService.register(
                    user.getUsername(),
                    user.getPassword(),
                    repeatPassword,
                    user.getName(),
                    user.getSurname(),
                    user.getRole() != null ? user.getRole() : Role.ROLE_USER
            );
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "404", description = "Invalid username or password")
    })
    @PostMapping("/login")
    public ResponseEntity<User> login(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userService.login(username, password);
            request.getSession().setAttribute("user", user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }
}
