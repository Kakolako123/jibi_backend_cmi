package com.example.cmi_backend.controller;

import com.example.cmi_backend.entity.Bank;
import com.example.cmi_backend.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    public Bank getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank);
    }

    @PutMapping("/{id}")
    public Bank updateBank(@PathVariable Long id, @RequestBody Bank bankDetails) {
        return bankService.updateBank(id, bankDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
    }
}
