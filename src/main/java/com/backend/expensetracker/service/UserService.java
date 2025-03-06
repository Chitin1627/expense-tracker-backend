package com.backend.expensetracker.service;

import com.backend.expensetracker.model.*;
import com.backend.expensetracker.model.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private JWTService jwtService;

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        else {
            return "Failed";
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetailsResponse getUserDetails(Authentication authentication) {
        String username = authentication.getName();
        User user = findByUsername(username);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        userDetailsResponse.setUsername(user.getUsername());
        userDetailsResponse.setEmail(user.getEmail());
        return userDetailsResponse;
    }

    public TokenResponse registerUser(User user) {
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

    public TokenResponse loginUser(User user) {
        String token = verify(user);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return tokenResponse;
    }

    public boolean isTokenValid(JwtToken token) {
        return !jwtService.isTokenExpired(token.getToken());
    }

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
