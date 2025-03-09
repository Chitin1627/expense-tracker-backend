package com.backend.expensetracker.service;

import com.backend.expensetracker.model.Debt;
import com.backend.expensetracker.model.repositories.DebtRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DebtService {

    private DebtRepository debtRepository;


    public Debt addDebt(Debt debt, Authentication authentication) {
        debt.setUserId(authentication.getName());
        debt.setCreated_at(LocalDateTime.now());
        debt.setCompleted(false);
        return debtRepository.save(debt);
    }

    public List<Debt> getDebtsByUser(Authentication authentication) {
        String username = authentication.getName();
        return debtRepository.findByUserId(username);
    }

    public ResponseEntity<String> deleteDebt(String id, Authentication authentication) {
        Optional<Debt> debt = debtRepository.findById(id);
        if(debt.isPresent() && debt.get().getUserId().equals(authentication.getName())) {
            debtRepository.delete(debt.get());
            return ResponseEntity.ok("Debt deleted");
        }
        else {
            return ResponseEntity.ok("Debt not found");
        }
    }

    public ResponseEntity<String> editDebt(Debt debt, Authentication authentication) {
        String username = authentication.getName();
        debt.setUserId(username);
        debt.setCreated_at(LocalDateTime.now());
        try {
            debtRepository.save(debt);
            return ResponseEntity.ok("Debt updated");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<String> changeStatus(String id, Authentication authentication) {
        Optional<Debt> debt = debtRepository.findById(id);
        if(debt.isPresent() && debt.get().getUserId().equals(authentication.getName())) {
            Debt updatedDebt = debt.get();
            updatedDebt.setCompleted(!updatedDebt.isCompleted());
            debtRepository.save(updatedDebt);
            return ResponseEntity.ok("Changed status of debt "+id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debt not found");

    }
}
