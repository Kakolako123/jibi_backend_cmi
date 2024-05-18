package com.example.cmi_backend.controller;

import com.example.cmi_backend.dto.PaymentAccountCreationRequest;
import com.example.cmi_backend.entity.PaymentAccount;
import com.example.cmi_backend.service.ApiResponse;
import com.example.cmi_backend.service.PaymentAccountException;
import com.example.cmi_backend.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-accounts")
public class PaymentAccountController {
    @Autowired
    private PaymentAccountService paymentAccountService;

    @GetMapping
    public ResponseEntity<List<PaymentAccount>> getAll() {
        List<PaymentAccount> paymentAccounts = paymentAccountService.findAll();
        return ResponseEntity.ok(paymentAccounts);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPaymentAccount(@RequestBody PaymentAccountCreationRequest request) {
        try {
            PaymentAccount paymentAccount = paymentAccountService.createPaymentAccount(request);
            String message = "Payment account created successfully.";
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(message, paymentAccount));
        } catch (PaymentAccountException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment account: " + e.getMessage());
        }
    }

    // Endpoint pour mettre à jour un compte de paiement
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaymentAccount(@PathVariable Long id, @RequestBody PaymentAccount updatedAccount) {
        try {
            PaymentAccount paymentAccount = paymentAccountService.update(id, updatedAccount);
            String message = "Payment account updated successfully.";
            return ResponseEntity.ok(new ApiResponse(message, paymentAccount));
        } catch (PaymentAccountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> getAccountBalance(@PathVariable Long id) {
        try {
            double balance = paymentAccountService.getAccountBalance(id);
            return ResponseEntity.ok(new ApiResponse("Account balance retrieved successfully.", balance));
        } catch (PaymentAccountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + e.getMessage());
        }
    }


    // Endpoint pour supprimer un compte de paiement
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentAccount(@PathVariable Long id) {
        try {
            PaymentAccount deletedAccount = paymentAccountService.delete(id);
            String message = "Payment account deleted successfully.";
            return ResponseEntity.ok(new ApiResponse(message, deletedAccount));
        } catch (PaymentAccountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + e.getMessage());
        }
    }
}
