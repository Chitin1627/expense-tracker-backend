package com.backend.expensetracker.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "debts")
public class Debt {
    @Id
    private String _id;
    private String userId;
    private String otherParty;
    private Double amount;
    private boolean receivable;
    private boolean completed;
    private String description;
    private LocalDateTime created_at;
}
