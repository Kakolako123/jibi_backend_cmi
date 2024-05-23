package com.example.cmi_backend.controller;

import com.example.cmi_backend.entity.Facture;
import com.example.cmi_backend.entity.Transaction;
import com.example.cmi_backend.repository.FactureRepository;
import com.example.cmi_backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/paiement")
public class PaiementController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private FactureRepository factureRepository;

    @GetMapping("/facture/{id}")
    public ResponseEntity<Facture> getFactureByIdAndCustomerId(
            @PathVariable Long id,
            @RequestParam Long customerId) {
        Optional<Facture> facture = paymentService.findFactureByIdAndCustomerId(id, customerId);
        return facture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/confirmer")
    public ResponseEntity<?> confirmerPaiement(@RequestParam Long factureId, @RequestParam String phoneNumber) {
        try {
            Transaction transaction = paymentService.confirmerPaiementAcc(factureId, phoneNumber);
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
