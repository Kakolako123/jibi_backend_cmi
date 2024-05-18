package com.example.jibi.repository;

import com.example.jibi.entity.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditorRepository extends JpaRepository<Creditor, Long> {
    Creditor findByCode(String code);
}
