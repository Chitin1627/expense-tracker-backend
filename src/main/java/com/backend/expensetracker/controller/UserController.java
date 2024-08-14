package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.User;
import com.backend.expensetracker.model.repositories.UserRepository;
import com.backend.expensetracker.service.MyUserDetailsService;
import com.backend.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Service
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    private Optional<User> findByUsername(String username) {
        return userRepository.findById(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    void registerUser(@RequestBody User user) {
        Optional<User> opUser = findByUsername(user.getUsername());
        if(opUser.isPresent()) {
            throw new RuntimeException("user already exists");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setCreated_at(LocalDateTime.now());
        userRepository.save(user);
    }

    @PostMapping("/authenticate")
    String loginUser(@RequestBody User user) {
//        Optional<User> opUser = findByUsername(user.getUsername());
//        if(opUser.isEmpty()) {
//            return false;
//        }
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        User dbUser = opUser.get();
//        return bCryptPasswordEncoder.matches(
//                user.getPassword(),
//                dbUser.getPassword()
//        );
        return service.verify(user);
    }

}
