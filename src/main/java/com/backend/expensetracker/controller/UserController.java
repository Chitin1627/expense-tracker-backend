package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.*;
import com.backend.expensetracker.model.repositories.UserRepository;
import com.backend.expensetracker.service.JWTService;
import com.backend.expensetracker.service.MyUserDetailsService;
import com.backend.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RestController
@Service
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    @Autowired
    private JWTService jwtService;

    private User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/user-details")
    UserDetailsResponse getUserDetails(Authentication authentication) {
        String username = authentication.getName();
        User user = findByUsername(username);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        userDetailsResponse.setUsername(user.getUsername());
        userDetailsResponse.setEmail(user.getEmail());
        return userDetailsResponse;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    TokenResponse registerUser(@RequestBody User user) {
        User opUser = findByUsername(user.getUsername());
        if(opUser!=null) {
            throw new RuntimeException("user already exists");
        }
        User oldUser = new User();
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setCreated_at(LocalDateTime.now());
        userRepository.save(user);
        return loginUser(oldUser);
    }

    @PostMapping("/authenticate")
    TokenResponse loginUser(@RequestBody User user) {
        String token = service.verify(user);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return tokenResponse;
    }

    @PostMapping("/validate-token")
    boolean isTokenValid(@RequestBody JwtToken token) {
        return !jwtService.isTokenExpired(token.getToken());
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody PasswordChangeRequest passwordChangeRequest,
            Authentication authentication
    ) {
        Optional<User> user = userRepository.findById(authentication.getName());
        if(user.isPresent()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User currUser = user.get();
            if(bCryptPasswordEncoder.matches(
                    passwordChangeRequest.getOldPassword(),
                    currUser.getPassword()
            )) {
                String newPassword = bCryptPasswordEncoder.encode(
                        passwordChangeRequest.getNewPassword()
                );
                currUser.setPassword(newPassword);
                userRepository.save(currUser);
                return ResponseEntity.ok("Password Changed Successfully");
            }
            else {
                return ResponseEntity.status(401).body("Current password is incorrect");
            }
        }
        else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
