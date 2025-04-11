package com.backend.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyExpense {
    private String month;
    private double total;
}
