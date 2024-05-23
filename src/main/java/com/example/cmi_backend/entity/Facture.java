package com.example.cmi_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroFacture;
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "creance_id")
    private Debt creance;

    @OneToOne(mappedBy = "facture")
    private Transaction transaction;

    // getters et setters
}
