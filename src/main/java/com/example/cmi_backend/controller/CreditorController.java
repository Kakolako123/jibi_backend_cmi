package com.example.cmi_backend.controller;


import com.example.cmi_backend.entity.Creditor;
import com.example.cmi_backend.service.CreditorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditors")
public class CreditorController {

    private final CreditorService creditorService;

    public CreditorController(CreditorService creditorService) {
        this.creditorService = creditorService;
    }

    @GetMapping
    public List<Creditor> getAllCreditors() {

        return creditorService.getAllCreditors();
    }

    @GetMapping("/{code}")
    public Creditor getCreditorByCode(@PathVariable String code) {

        return creditorService.getCreditorByCode(code);
    }
}
