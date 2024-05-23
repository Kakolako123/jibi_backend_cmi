package com.example.cmi_backend.repository;

import com.example.cmi_backend.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    Optional<Facture> findByIdAndCustomerId(Long id, Long customerId);
}
