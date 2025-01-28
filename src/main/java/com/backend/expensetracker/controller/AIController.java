package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.Category;
import com.backend.expensetracker.model.Expense;
import com.backend.expensetracker.model.UserSpending;
import com.backend.expensetracker.service.QnaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ai")
public class AIController {

    private final QnaService qnaService;

    private final ExpenseController expenseController;

    private final CategoryController categoryController;

    private final UserSpendingController userSpendingController;

    @GetMapping("")
    public ResponseEntity<String> askQuestion(Authentication authentication) {
        UserSpending userSpending = userSpendingController.getUserSpending(authentication);
        StringBuilder prompt = new StringBuilder("Analyze these expenses and suggest ways to cut costs under monthly limit");
        prompt.append(userSpending.getMonthlyLimit());
        prompt.append("point-wise and limit to 5 points: (everything is in Rupees)\n");

        List<Expense> expenses = expenseController.getExpenseLast6Months(authentication);
        List<Category> categoriesList = categoryController.findAll();

        Map<String, String> categories = categoriesList.stream()
                .collect(Collectors.toMap(Category::get_id, Category::getName));

        for (Expense expense : expenses) {
            prompt.append(String.format("Category: %s, Amount: %.2f, Date: %s, Description: %s\n",
                    categories.get(expense.getCategory_id()),
                    expense.getAmount(),
                    expense.getDate(),
                    expense.getDescription()));
        }

        String answer = qnaService.getAnswer(prompt.toString());
        return ResponseEntity.ok(answer);
    }
}
