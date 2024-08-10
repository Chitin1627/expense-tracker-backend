package com.backend.expensetracker.model.repositories;

import com.backend.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByUsernameAndDateBetween(String username, LocalDateTime startDate, LocalDateTime endDate);

    List<Expense> findByUsername(String username);

}
