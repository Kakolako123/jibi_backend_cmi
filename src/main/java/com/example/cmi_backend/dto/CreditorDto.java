package com.example.cmi_backend.dto;

import com.example.cmi_backend.dto.DebtDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditorDto {
    private Long code;
    private String name;
    private String category;
    private List<DebtDto> debts;
    // Getters and Setters
}
