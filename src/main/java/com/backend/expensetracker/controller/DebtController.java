package com.backend.expensetracker.controller;

import com.backend.expensetracker.model.Debt;
import com.backend.expensetracker.model.repositories.DebtRepository;
import com.backend.expensetracker.service.DebtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/debt")
public class DebtController {
    private DebtService debtService;

    @PostMapping("/add")
    public Debt addDebt(@RequestBody Debt debt, Authentication authentication) {
        return debtService.addDebt(debt, authentication);
    }

    @GetMapping("/get")
    public List<Debt> getDebtsByUser(Authentication authentication) {
        return debtService.getDebtsByUser(authentication);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDebt(@RequestParam String id, Authentication authentication) {
        return debtService.deleteDebt(id, authentication);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> updateDebt(@RequestBody Debt debt, Authentication authentication) {
        return debtService.editDebt(debt, authentication);
    }

    @PutMapping("/change-status")
    public ResponseEntity<String> changeStatus(@RequestParam String id, Authentication authentication) {
        return debtService.changeStatus(id, authentication);
    }
}
