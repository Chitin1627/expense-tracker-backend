package com.backend.expensetracker.controller;

import com.backend.expensetracker.service.AIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    @GetMapping("")
    public ResponseEntity<String> askQuestion(Authentication authentication) {
        return aiService.askQuestion(authentication);
    }
}
