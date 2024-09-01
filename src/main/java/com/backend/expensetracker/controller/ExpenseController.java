package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.DateUtils;
import com.backend.expensetracker.model.Expense;
import com.backend.expensetracker.model.PasswordChangeRequest;
import com.backend.expensetracker.model.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Service
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping("")
    void create(@RequestBody Expense expense, Authentication authentication) {
        expense.setUsername(authentication.getName());
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
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        startDate = calendar.getTime();

        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        endDate = calendar.getTime();
        System.out.println(endDate);
        return expenseRepository.findByUsernameAndDateBetween(username, startDate, endDate);
    }

    @GetMapping("/user-expenses")
    public List<Expense> getExpenseByUsername(
            Authentication authentication
    ) {
        String username = authentication.getName();
        return expenseRepository.findByUsername(authentication.getName());
    }

    @GetMapping("/user-expenses/current-month")
    public List<Expense> getExpenseThisMonth(
            Authentication authentication
    ) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MILLISECOND, -1);
        Date startOfMonth = calendar.getTime();

        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        Date endOfMonth = calendar.getTime();

        System.out.println(startOfMonth);
        System.out.println(endOfMonth);
        String username = authentication.getName();

        return expenseRepository.findByUsernameAndDateBetween(username, startOfMonth, endOfMonth);
    }

    @GetMapping("/user-expenses/last-six-months")
    public List<Expense> getExpenseLast6Months(
            Authentication authentication
    ) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, -6);
        Date startOfMonth = calendar.getTime();

        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endOfMonth = calendar.getTime();

        System.out.println(startOfMonth);
        System.out.println(endOfMonth);
        String username = authentication.getName();

        return expenseRepository.findByUsernameAndDateBetween(username, startOfMonth, endOfMonth);
    }

    @GetMapping("/user-expenses/previous-month")
    public List<Expense> getExpensePreviousMonth(
            Authentication authentication
    ) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.MILLISECOND, -1);
        Date startOfMonth = calendar.getTime();

        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date endOfMonth = calendar.getTime();

        System.out.println(startOfMonth);
        System.out.println(endOfMonth);
        String username = authentication.getName();

        return expenseRepository.findByUsernameAndDateBetween(username, startOfMonth, endOfMonth);
    }

    @GetMapping("/user-expenses/date")
    List<Expense> getExpensesByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            Authentication authentication
    ) {
        String username = authentication.getName();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date startDate = calendar.getTime();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = calendar.getTime();
        return expenseRepository.findByUsernameAndDateBetween(username, startDate, endDate);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteExpense(
            @RequestParam String id,
            Authentication authentication
    ) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent() && Objects.equals(expense.get().getUsername(), authentication.getName())) {
            expenseRepository.deleteById(id);
            return ResponseEntity.ok("Expense deleted successfully");
        }
        else {
            return ResponseEntity.status(404).body("Expense not found");
        }
    }
}
