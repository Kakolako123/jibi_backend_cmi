package com.example.cmi_backend.controller;

import com.example.cmi_backend.service.ImpayeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/impayes")
public class ImpayeController {

    private final ImpayeService impayeService;

    public ImpayeController(ImpayeService impayeService) {
        this.impayeService = impayeService;
    }

    @GetMapping("/{debtCode}")
    public Map<String, Object> getImpayesByDebtCode(@PathVariable Long debtCode) {
        return impayeService.getImpayesByDebtCode(debtCode);
    }
}
