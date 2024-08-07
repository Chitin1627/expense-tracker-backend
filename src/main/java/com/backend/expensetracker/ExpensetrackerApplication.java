package com.backend.expensetracker;

import com.backend.expensetracker.model.ExpenseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ExpensetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensetrackerApplication.class, args);
	}

}
