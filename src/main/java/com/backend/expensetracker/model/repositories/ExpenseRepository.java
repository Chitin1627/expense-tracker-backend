package com.backend.expensetracker.model.repositories;

import com.backend.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
