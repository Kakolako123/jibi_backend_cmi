package com.example.cmi_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "factureId")
    private Facture facture;
    private double montant;
    private Date date;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentAccountId")
    private PaymentAccount paymentAccount;
}
