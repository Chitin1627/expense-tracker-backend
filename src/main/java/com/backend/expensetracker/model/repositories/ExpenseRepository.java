package com.backend.expensetracker.model.repositories;

import com.backend.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByUsernameAndDateBetween(String username, Date startDate, Date endDate);

    List<Expense> findByUsername(String username);


}
