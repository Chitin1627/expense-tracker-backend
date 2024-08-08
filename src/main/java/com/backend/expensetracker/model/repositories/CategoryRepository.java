package com.backend.expensetracker.model.repositories;

import com.backend.expensetracker.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
