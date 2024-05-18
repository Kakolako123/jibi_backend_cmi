package com.example.jibi.service;

import com.example.jibi.model.Creditor;
import java.util.List;

public interface CreditorService {

    List<Creditor> getAllCreditors();

    Creditor getCreditorByCode(String code);
}
