package com.example.cmi_backend.repository;

import com.example.cmi_backend.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
