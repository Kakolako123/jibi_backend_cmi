package com.example.cmi_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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
    @OneToMany(mappedBy = "paymentAccount")
    private List<Transaction> transactions;
}
