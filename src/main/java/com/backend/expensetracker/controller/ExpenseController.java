package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.Expense;
import com.backend.expensetracker.model.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@Service
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping("")
    void create(@RequestBody Expense expense) {
        expense.setCreated_at(LocalDateTime.now());
        expenseRepository.save(expense);
    }

    @GetMapping("/date")
    public List<Expense> getExpensesByUsernameBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            Authentication authentication
    ) {
        String username = authentication.getName();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        startDate = calendar.getTime();

        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        endDate = calendar.getTime();

        return expenseRepository.findByUsernameAndDateBetween(username, startDate, endDate);
    }

    @GetMapping("/user-expenses")
    public List<Expense> getExpenseByUsername(
            Authentication authentication
    ) {
        String username = authentication.getName();
        return expenseRepository.findByUsername(authentication.getName());
    }
}
