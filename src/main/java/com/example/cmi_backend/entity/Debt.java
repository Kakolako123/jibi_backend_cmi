package com.example.cmi_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @JsonBackReference
    private Creditor creditor;

    @OneToMany(mappedBy = "creance")
    private List<Impaye> impayes;
}
