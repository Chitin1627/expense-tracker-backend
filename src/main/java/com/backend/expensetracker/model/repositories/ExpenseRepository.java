package com.backend.expensetracker.model.repositories;

import com.backend.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
}
