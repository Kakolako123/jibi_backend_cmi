package com.example.cmi_backend.service;

import com.example.cmi_backend.entity.Creditor;

import com.example.cmi_backend.repository.CreditorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public Creditor getCreditorByCode(String code) {
        return creditorRepository.findByCode(code);
    }
}
