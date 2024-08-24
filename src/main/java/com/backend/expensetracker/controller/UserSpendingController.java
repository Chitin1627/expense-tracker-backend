package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.UserSpendingRequest;
import com.backend.expensetracker.model.UserSpending;
import com.backend.expensetracker.model.repositories.UserSpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Service
@RequestMapping("/api/user-spending")
public class UserSpendingController {
    @Autowired
    private UserSpendingRepository spendingRepository;

    @GetMapping("")
    public UserSpending getUserSpending(Authentication authentication) {
        String username = authentication.getName();
        Optional<UserSpending> userSpending = spendingRepository.findByUsername(username);
        return userSpending.orElseGet(UserSpending::new);
    }

    @PostMapping("")
    public UserSpending setMonthlyLimit(
            @RequestBody UserSpendingRequest request,
            Authentication authentication
    ) {
        Optional<UserSpending> optionalUserSpending = spendingRepository.findByUsername(authentication.getName());
        UserSpending userSpending;
        if (optionalUserSpending.isPresent()) {
            userSpending = optionalUserSpending.get();
        } else {
            userSpending = new UserSpending();
            userSpending.setUsername(
                    authentication.getName()
            );
        }

        userSpending.setMonthlyLimit(request.getNewLimit());
        return spendingRepository.save(userSpending);
    }
}
