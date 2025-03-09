package com.backend.expensetracker.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "categories")
public class Category {
    @Id
    private String _id;
    private String name;
    private String description;
    private LocalDateTime created_at;
}
