package com.backend.expensetracker.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;


@Document(collection = "expenses")
public class Expense {
        Double price;
        Category category;

        public Double getPrice() {
                return price;
        }

        public void setPrice(Double price) {
                this.price = price;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }

        LocalDate date;
}
