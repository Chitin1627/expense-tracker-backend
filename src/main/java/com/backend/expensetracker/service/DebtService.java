package com.backend.expensetracker.service;

import com.backend.expensetracker.model.Debt;
import com.backend.expensetracker.model.repositories.DebtRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class DebtService {

    private DebtRepository debtRepository;


    public Debt addDebt(Debt debt, Authentication authentication) {
        debt.setUserId(authentication.getName());
        debt.setCreated_at(LocalDateTime.now());
        return debtRepository.save(debt);
    }

    public List<Debt> getDebtsByUser(Authentication authentication) {
        String username = authentication.getName();
        return debtRepository.findByUserId(username);
    }
}
