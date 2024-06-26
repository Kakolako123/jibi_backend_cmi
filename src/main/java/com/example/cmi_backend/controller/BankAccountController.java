package com.example.cmi_backend.controller;

import com.example.cmi_backend.entity.BankAccount;
import com.example.cmi_backend.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id);
    }

    @PostMapping("/client/{clientId}")
    public BankAccount createBankAccount(@PathVariable Long clientId, @RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(clientId, bankAccount);
    }

    @PutMapping("/{id}")
    public BankAccount updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccountDetails) {
        return bankAccountService.updateBankAccount(id, bankAccountDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
    }
}
