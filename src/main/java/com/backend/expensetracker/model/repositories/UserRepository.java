package com.backend.expensetracker.model.repositories;

import com.backend.expensetracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
