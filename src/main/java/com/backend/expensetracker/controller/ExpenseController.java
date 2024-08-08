package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.Expense;
import com.backend.expensetracker.model.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("")
    List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @PostMapping("")
    void create(@RequestBody Expense expense) {expenseRepository.save(expense);}

}
