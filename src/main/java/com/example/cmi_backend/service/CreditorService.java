package com.example.cmi_backend.service;

import com.example.cmi_backend.entity.Creditor;

import java.util.List;

public interface CreditorService {

    List<Creditor> getAllCreditors();

    Creditor getCreditorByCode(Long code);
}
