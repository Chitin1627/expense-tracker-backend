package com.backend.expensetracker.service;

import com.backend.expensetracker.model.Category;
import com.backend.expensetracker.model.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> createCategory(@RequestBody List<Category> categories) {
        categories.forEach(category -> category.setCreated_at(LocalDateTime.now()));
        return categoryRepository.saveAll(categories);
    }
}
