package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.*;
import com.backend.expensetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/user-details")
    UserDetailsResponse getUserDetails(Authentication authentication) {
        return userService.getUserDetails(authentication);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    TokenResponse registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/authenticate")
    TokenResponse loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @PostMapping("/validate-token")
    boolean isTokenValid(@RequestBody JwtToken token) {
        return userService.isTokenValid(token);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody PasswordChangeRequest passwordChangeRequest,
            Authentication authentication
    ) {
        return userService.changePassword(passwordChangeRequest, authentication);
    }
}
