package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.Expense;
import com.backend.expensetracker.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    @PostMapping("")
    void create(@RequestBody Expense expense, Authentication authentication) {
        expenseService.create(expense, authentication);
    }

    @GetMapping("/date")
    public List<Expense> getExpensesByUsernameBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            Authentication authentication
    ) {
        return expenseService.getExpensesByUsernameBetweenDates(startDate, endDate, authentication);
    }

    @GetMapping("/user-expenses")
    public List<Expense> getExpenseByUsername(
            Authentication authentication
    ) {
        return expenseService.getExpenseByUsername(authentication);
    }

    @GetMapping("/user-expenses/current-month")
    public List<Expense> getExpenseThisMonth(
            Authentication authentication
    ) {
        return expenseService.getExpenseThisMonth(authentication);
    }

    @GetMapping("/user-expenses/last-six-months")
    public List<Expense> getExpenseLast6Months(
            Authentication authentication
    ) {
        return expenseService.getExpenseLast6Months(authentication);
    }

    @GetMapping("/user-expenses/previous-month")
    public List<Expense> getExpensePreviousMonth(
            Authentication authentication
    ) {
        return expenseService.getExpensePreviousMonth(authentication);
    }

    @GetMapping("/user-expenses/date")
    public List<Expense> getExpensesByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            Authentication authentication
    ) {
        return expenseService.getExpensesByDate(date, authentication);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteExpense(
            @RequestParam String id,
            Authentication authentication
    ) {
        return expenseService.deleteExpense(id, authentication);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editExpense(
            @RequestBody Expense expense,
            Authentication authentication
    ) {
        return expenseService.editExpense(expense, authentication);
    }
}
