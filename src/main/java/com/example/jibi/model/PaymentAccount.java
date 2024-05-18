package com.example.jibi.model;

import com.example.jibi.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class PaymentAccount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long paymentAccountId;
    @OneToOne
    @JoinColumn(name="customer_id", referencedColumnName="id")
    private Customer customer;
    private double accountBalance;
    private LocalDate createdDate;
    private String bankName;
    private String PhoneNumber;
}