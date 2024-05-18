package com.example.jibi.controller;

import com.example.jibi.service.ImpayeService;
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
    public Map<String, Object> getImpayesByDebtCode(@PathVariable String debtCode) {
        return impayeService.getImpayesByDebtCode(debtCode);
    }
}
