package com.backend.expensetracker.model.repositories;

import com.backend.expensetracker.model.UserSpending;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserSpendingRepository extends MongoRepository<UserSpending, String> {
    Optional<UserSpending> findByUsername(String username);
}
