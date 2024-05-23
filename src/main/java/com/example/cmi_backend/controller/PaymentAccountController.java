//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompile

package com.example.cmi_backend.controller;

import com.example.cmi_backend.dto.PaymentAccountCreationRequest;
import com.example.cmi_backend.entity.PaymentAccount;

import java.util.List;

import com.example.cmi_backend.service.ApiResponse;
import com.example.cmi_backend.service.PaymentAccountException;
import com.example.cmi_backend.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/payment-accounts"})
public class PaymentAccountController {
    @Autowired
    private PaymentAccountService paymentAccountService;

    public PaymentAccountController() {
    }

    @GetMapping
    public ResponseEntity<List<PaymentAccount>> getAll() {
        List<PaymentAccount> paymentAccounts = this.paymentAccountService.findAll();
        return ResponseEntity.ok(paymentAccounts);
    }

    @PostMapping({"/create"})
    public ResponseEntity<?> createPaymentAccount(@RequestBody PaymentAccountCreationRequest request) {
        try {
            PaymentAccount paymentAccount = this.paymentAccountService.createPaymentAccount(request);
            String message = "Payment account created successfully.";
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(message, paymentAccount));
        } catch (PaymentAccountException var4) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment account: " + var4.getMessage());
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> updatePaymentAccount(@PathVariable Long id, @RequestBody PaymentAccount updatedAccount) {
        try {
            PaymentAccount paymentAccount = this.paymentAccountService.update(id, updatedAccount);
            String message = "Payment account updated successfully.";
            return ResponseEntity.ok(new ApiResponse(message, paymentAccount));
        } catch (PaymentAccountException var5) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + var5.getMessage());
        }
    }

    @GetMapping({"/{id}/balance"})
    public ResponseEntity<?> getAccountBalance(@PathVariable Long id) {
        try {
            double balance = this.paymentAccountService.getAccountBalance(id);
            return ResponseEntity.ok(new ApiResponse("Account balance retrieved successfully.", balance));
        } catch (PaymentAccountException var4) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + var4.getMessage());
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deletePaymentAccount(@PathVariable Long id) {
        try {
            PaymentAccount deletedAccount = this.paymentAccountService.delete(id);
            String message = "Payment account deleted successfully.";
            return ResponseEntity.ok(new ApiResponse(message, deletedAccount));
        } catch (PaymentAccountException var4) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + var4.getMessage());
        }
    }
}
