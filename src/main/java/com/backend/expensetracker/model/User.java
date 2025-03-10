package com.backend.expensetracker.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document(collection = "users")
public class User {
    @Id
    private String username;
    private String email;
    private String password;
    private LocalDateTime created_at;
}
