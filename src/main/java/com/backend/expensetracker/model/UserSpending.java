package com.backend.expensetracker.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "user_spending")
public class UserSpending {
    @Id
    private String id;
    private String username;
    private double monthlyLimit;
}
