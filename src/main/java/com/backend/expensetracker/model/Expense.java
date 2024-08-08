package com.backend.expensetracker.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "expenses")
public class Expense {
        @Id
        private String _id;
        private String user_id;
        private String category_id;
        private Double amount;
        private String description;
        private LocalDateTime date;
        private LocalDateTime created_at;

        public String get_id() {
                return _id;
        }

        public void set_id(String _id) {
                this._id = _id;
        }

        public String getUser_id() {
                return user_id;
        }

        public void setUser_id(String user_id) {
                this.user_id = user_id;
        }

        public String getCategory_id() {
                return category_id;
        }

        public void setCategory_id(String category_id) {
                this.category_id = category_id;
        }

        public Double getAmount() {
                return amount;
        }

        public void setAmount(Double amount) {
                this.amount = amount;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDateTime getDate() {
                return date;
        }

        public void setDate(LocalDateTime date) {
                this.date = date;
        }

        public LocalDateTime getCreated_at() {
                return created_at;
        }

        public void setCreated_at(LocalDateTime created_at) {
                this.created_at = created_at;
        }
}
