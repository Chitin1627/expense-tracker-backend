package com.backend.expensetracker.service;


import com.backend.expensetracker.model.repositories.ExpenseRepository;
import com.backend.expensetracker.model.repositories.UserRepository;
import com.backend.expensetracker.model.repositories.UserSpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDeletionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserSpendingRepository userSpendingRepository;

    @Transactional
    public void deleteUserAndRelatedData(String username) {
        expenseRepository.deleteByUsername(username);
        userSpendingRepository.deleteByUsername(username);
        userRepository.deleteByUsername(username);
    }
}
