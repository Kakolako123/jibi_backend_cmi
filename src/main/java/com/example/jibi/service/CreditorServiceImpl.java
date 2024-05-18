package com.example.jibi.service;

import com.example.jibi.model.Creditor;
import com.example.jibi.repository.CreditorRepository;
import com.example.jibi.service.CreditorService;
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
