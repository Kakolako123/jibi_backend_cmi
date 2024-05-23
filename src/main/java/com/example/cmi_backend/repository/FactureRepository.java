package com.example.cmi_backend.repository;

import com.example.cmi_backend.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {}