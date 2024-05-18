package com.example.cmi_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String description;
    private boolean paid;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "paymentAccountId")
    private PaymentAccount paymentAccount;
}
