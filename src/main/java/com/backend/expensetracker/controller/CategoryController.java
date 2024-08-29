package com.backend.expensetracker.controller;


import com.backend.expensetracker.model.Category;
import com.backend.expensetracker.model.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@Service
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("")
    List<Category> createCategory(@RequestBody List<Category> categories) {
        categories.forEach(category -> category.setCreated_at(LocalDateTime.now()));
        return categoryRepository.saveAll(categories);
    }
}
