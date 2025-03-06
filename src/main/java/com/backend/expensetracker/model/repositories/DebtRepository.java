package com.backend.expensetracker.model.repositories;
import com.backend.expensetracker.model.Debt;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DebtRepository extends MongoRepository<Debt, String> {
    List<Debt> findByUserId(String userId);
}
