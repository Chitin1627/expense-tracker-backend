package com.backend.expensetracker.controller;


import com.backend.expensetracker.model.Category;
import com.backend.expensetracker.model.repositories.CategoryRepository;
import com.backend.expensetracker.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("")
    public List<Category> createCategory(@RequestBody List<Category> categories) {
        return categoryService.createCategory(categories);
    }
}
