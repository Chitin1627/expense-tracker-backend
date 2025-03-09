package com.backend.expensetracker.service;

import com.backend.expensetracker.model.Expense;
import com.backend.expensetracker.model.repositories.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    public void create(Expense expense, Authentication authentication) {
        expense.setUsername(authentication.getName());
        expense.setCreated_at(LocalDateTime.now());
        expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUsernameBetweenDates(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
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

    public List<Expense> getExpenseByUsername(
            Authentication authentication
    ) {
        String username = authentication.getName();
        return expenseRepository.findByUsername(authentication.getName());
    }

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

        String username = authentication.getName();

        return expenseRepository.findByUsernameAndDateBetween(username, startOfMonth, endOfMonth);
    }

    public List<Expense> getExpenseLast6Months(
            Authentication authentication
    ) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, -5);
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

    public List<Expense> getExpensesByDate(
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

    public ResponseEntity<String> deleteExpense(
            String id,
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

    public ResponseEntity<String> editExpense(
            Expense expense,
            Authentication authentication
    ) {
        expense.setUsername(authentication.getName());
        try {
            expenseRepository.save(expense);
            return ResponseEntity.ok("Expense edited successfully");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

