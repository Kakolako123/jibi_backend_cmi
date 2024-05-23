package com.example.cmi_backend.service;

import com.example.cmi_backend.dto.CreditorDto;
import com.example.cmi_backend.dto.DebtDto;
import com.example.cmi_backend.entity.Creditor;

import com.example.cmi_backend.entity.Debt;
import com.example.cmi_backend.repository.CreditorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditorServiceImpl implements CreditorService {

    private final CreditorRepository creditorRepository;

    public CreditorServiceImpl(CreditorRepository creditorRepository) {
        this.creditorRepository = creditorRepository;
    }

    @Override
    public List<Creditor> getAllCreditors() {
        return creditorRepository.findAll();
    }

    @Override
    public Creditor getCreditorByCode(Long code) {
        return creditorRepository.findByCodeCreditor(code);
    }
    private CreditorDto convertToDto(Creditor creditor) {
        CreditorDto dto = new CreditorDto();
        dto.setCode(creditor.getCodeCreditor());
        dto.setName(creditor.getCreditorName());
        dto.setCategory(creditor.getCategory());
        dto.setDebts(creditor.getListDebts().stream().map(this::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    private DebtDto convertToDto(Debt debt) {
        DebtDto dto = new DebtDto();
        dto.setCode(debt.getCodeDebt());
        dto.setName(debt.getDebtName());
        return dto;
    }
}
