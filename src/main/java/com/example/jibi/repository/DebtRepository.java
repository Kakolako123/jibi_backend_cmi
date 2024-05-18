package com.example.jibi.repository;

import com.example.jibi.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    Debt findByCode(String code);
}
