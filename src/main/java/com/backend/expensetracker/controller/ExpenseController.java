package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.Expense;
import com.backend.expensetracker.model.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    void create(@RequestBody Expense expense) {
        expenseRepository.save(expense);
    }

    @GetMapping("/date")
    public List<Expense> getExpensesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        startDate = startDate.minusDays(1);
        endDate = endDate.plusDays(1);
        return expenseRepository.findByDateBetween(startDate, endDate);
    }
}
