package com.example.cmi_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeDebt;
    private Double amount;
    private String debtName;
    @ManyToOne
    @JoinColumn(name = "creditor_id")
    private Creditor creditor;
}
