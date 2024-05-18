package com.example.cmi_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Creditor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeCreditor;
    private String creditorName;
    private String category;
    @OneToMany(mappedBy = "creditor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Debt> listDebts;

}
