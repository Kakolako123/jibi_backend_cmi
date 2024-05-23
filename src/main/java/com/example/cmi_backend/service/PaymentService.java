package com.example.cmi_backend.service;

import com.example.cmi_backend.dto.TransactionStatus;
import com.example.cmi_backend.entity.Facture;
import com.example.cmi_backend.entity.PaymentAccount;
import com.example.cmi_backend.entity.Transaction;
import com.example.cmi_backend.repository.CustomerRepository;
import com.example.cmi_backend.repository.FactureRepository;
import com.example.cmi_backend.repository.PaymentAccountRepository;
import com.example.cmi_backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction confirmerPaiementAcc(Long factureId, String phoneNumber) throws IllegalArgumentException {
        Optional<Facture> factureOpt = factureRepository.findById(factureId);
        if (factureOpt.isPresent()) {
            Facture facture = factureOpt.get();
            PaymentAccount paymentAccount = facture.getPaymentAccount();

            if (paymentAccount != null && paymentAccount.getPhoneNumber().equals(phoneNumber) &&
                    paymentAccount.getAccountBalance() >= facture.getTotalAmount() && !facture.isPaid()) {

                // Mettre à jour le solde du compte de paiement
                paymentAccount.setAccountBalance(paymentAccount.getAccountBalance() - facture.getTotalAmount());

                // Marquer la facture comme payée
                facture.setPaid(true);

                // Créer et enregistrer la transaction
                Transaction transaction = Transaction.builder()
                        .facture(facture)
                        .montant(facture.getTotalAmount())
                        .date(new Date())
                        .status(String.valueOf(TransactionStatus.SUCCEEDED))
                        .paymentAccount(paymentAccount)
                        .build();

                paymentAccount.getTransactions().add(transaction);

                paymentAccountRepository.save(paymentAccount);
                factureRepository.save(facture);
                return transactionRepository.save(transaction);
            }
        }
        throw new IllegalArgumentException("Erreur lors de la confirmation du paiement");
    }
    public Optional<Facture> findFactureByIdAndCustomerId(Long id, Long customerId) {
        return factureRepository.findByIdAndCustomerId(id, customerId);
    }
}
