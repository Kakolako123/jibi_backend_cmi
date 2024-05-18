package com.example.jibi.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ImpayeService {

    public Map<String, Object> getImpayesByDebtCode(String debtCode) {
        // Logique pour récupérer les impayés basé sur le code de la créance
        return Map.of(
                "impayeType", "simple",
                "amount", 100.0,
                "penalty", 10.0,
                "fees", 5.0
        );
    }
}
