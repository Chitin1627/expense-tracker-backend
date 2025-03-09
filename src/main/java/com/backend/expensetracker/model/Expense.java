package com.backend.expensetracker.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Document(collection = "expenses")
public class Expense {
        @Id
        private String _id;
        private String username;
        private String category_id;
        private Double amount;
        private String description;
        private TransactionType type;
        private Date date;
        private LocalDateTime created_at;
}
