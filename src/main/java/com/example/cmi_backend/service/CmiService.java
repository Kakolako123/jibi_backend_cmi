package com.example.cmi_backend.service;

import com.example.cmi_backend.entity.*;
import com.example.cmi_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CmiService {

    @Autowired
    private CreditorRepository creancierRepository;
    @Autowired
    private DebtRepository creanceRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private ImpayeRepository impayeRepository;
    @Autowired
    private CustomerRepository clientRepository;
    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    public List<Creditor> getAllCreanciers() {
        return creancierRepository.findAll();
    }

    public Form getFormByCreanceId(Long creanceId) {
        return formRepository.findById(creanceId).orElse(null);
    }

    public List<Impaye> getImpayesByCreanceId(Long creanceId) {
        Debt creance = creanceRepository.findById(creanceId).orElse(null);
        if (creance != null) {
            return impayeRepository.findByCreance(creance);
        }
        return null;
    }

    public boolean confirmePayer(Long impayeId, String phoneNumber) {
        Customer client = clientRepository.findByPhoneNumber(phoneNumber);
        if (client == null) {
            return false;
        }

        Impaye impaye = impayeRepository.findById(impayeId).orElse(null);
        if (impaye == null) {
            return false;
        }

        PaymentAccount account = client.getPaymentAccount();
        if (account.getAccountBalance() >= impaye.getMontant()) {
            account.setAccountBalance(account.getAccountBalance() - impaye.getMontant());
            paymentAccountRepository.save(account);
            impayeRepository.delete(impaye);

            // Créer une transaction
            Transaction transaction = new Transaction();
            transaction.setMontant(impaye.getMontant());
            transaction.setType("Payment");
            transaction.setPaymentAccount(account);
            transactionRepository.save(transaction);

            return true;
        }

        return false;
    }
}
