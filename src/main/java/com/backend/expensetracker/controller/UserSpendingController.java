package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.UserSpendingRequest;
import com.backend.expensetracker.model.UserSpending;
import com.backend.expensetracker.model.repositories.UserSpendingRepository;
import com.backend.expensetracker.service.UserService;
import com.backend.expensetracker.service.UserSpendingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user-spending")
public class UserSpendingController {
    private final UserSpendingService userSpendingService;

    @GetMapping("")
    public UserSpending getUserSpending(Authentication authentication) {
        return userSpendingService.getUserSpending(authentication);
    }

    @PostMapping("")
    public UserSpending setMonthlyLimit(
            @RequestBody UserSpendingRequest request,
            Authentication authentication
    ) {
        return userSpendingService.setMonthlyLimit(request, authentication);
    }
}
