package com.backend.expensetracker.service;

import com.backend.expensetracker.model.UserSpending;
import com.backend.expensetracker.model.UserSpendingRequest;
import com.backend.expensetracker.model.repositories.UserSpendingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSpendingService {
    private UserSpendingRepository spendingRepository;

    public UserSpending getUserSpending(Authentication authentication) {
        String username = authentication.getName();
        Optional<UserSpending> userSpending = spendingRepository.findByUsername(username);
        return userSpending.orElseGet(UserSpending::new);
    }

    public UserSpending setMonthlyLimit(
            UserSpendingRequest request,
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
