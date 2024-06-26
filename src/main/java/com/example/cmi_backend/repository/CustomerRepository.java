package com.example.cmi_backend.repository;


import com.example.cmi_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Customer findByPhoneNumber(String phoneNumber);
}
