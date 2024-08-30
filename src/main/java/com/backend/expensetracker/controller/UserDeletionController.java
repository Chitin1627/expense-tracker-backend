package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.repositories.ExpenseRepository;
import com.backend.expensetracker.model.repositories.UserRepository;
import com.backend.expensetracker.model.repositories.UserSpendingRepository;
import com.backend.expensetracker.service.UserDeletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delete-user")
public class UserDeletionController {
    @Autowired
    private UserDeletionService userDeletionService;
    @DeleteMapping("")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        String username = authentication.getName();
        try {
            userDeletionService.deleteUserAndRelatedData(username);
            return ResponseEntity.ok("All data related to username '" + username + "' has been deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting data: " + e.getMessage());
        }
    }
}
