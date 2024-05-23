package com.example.cmi_backend.repository;


import com.example.cmi_backend.entity.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditorRepository extends JpaRepository<Creditor, Long> {
    Creditor findByCodeCreditor(String code);
}
