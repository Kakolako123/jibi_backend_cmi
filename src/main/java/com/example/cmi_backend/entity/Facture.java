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
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalAmount;
    private String description;
    private boolean paid;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "paymentAccountId")
    private PaymentAccount paymentAccount;

    @OneToOne(mappedBy = "facture")
    private Transaction transaction;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
