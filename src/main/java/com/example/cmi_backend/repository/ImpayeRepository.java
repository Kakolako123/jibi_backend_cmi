package com.example.cmi_backend.repository;

import com.example.cmi_backend.entity.Debt;
import com.example.cmi_backend.entity.Impaye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpayeRepository extends JpaRepository<Impaye, Long> {
    List<Impaye> findByCreance(Debt creance);
}