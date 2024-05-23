package com.example.cmi_backend.entity;

import com.example.cmi_backend.entity.Debt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Impaye {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private String type;
    @ManyToOne
    @JoinColumn(name = "creance_id")
    private Debt creance;


    // getters et setters
}
