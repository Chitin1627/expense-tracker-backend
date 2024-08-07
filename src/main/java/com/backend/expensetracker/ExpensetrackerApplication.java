package com.backend.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableMongoRepositories
public class ExpensetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensetrackerApplication.class, args);
	}

}
