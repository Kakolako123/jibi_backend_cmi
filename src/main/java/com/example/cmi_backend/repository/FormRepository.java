package com.example.cmi_backend.repository;

import com.example.cmi_backend.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {}