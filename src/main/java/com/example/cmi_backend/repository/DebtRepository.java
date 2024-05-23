package com.example.cmi_backend.repository;


import com.example.cmi_backend.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    Debt findBycodeDebt(Long code);
}
