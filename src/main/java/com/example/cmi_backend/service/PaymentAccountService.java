package com.example.cmi_backend.service;

import com.example.cmi_backend.model.PaymentAccountCreationRequest;
import com.example.cmi_backend.entity.Customer;
import com.example.cmi_backend.entity.PaymentAccount;
import com.example.cmi_backend.repositery.CustomerRepository;
import com.example.cmi_backend.repositery.PaymentAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentAccountService {
    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public PaymentAccount createPaymentAccount(PaymentAccountCreationRequest request) {
        Logger logger = LoggerFactory.getLogger(PaymentAccountService.class);
        logger.info("Recherche du client avec le numéro de téléphone: " + request.getPhoneNumber());

        Optional<Customer> existingCustomer = customerRepository.findByPhoneNumber(request.getPhoneNumber());
        logger.info("Résultat de la recherche du client: " + existingCustomer);

        //On voit si le client existe
        Customer customer;
        if (existingCustomer.isPresent()) {
            customer = existingCustomer.get();
        } else {
            // Si le client n'existe pas, lancer une exception que ce client cherche on ne peut pas lui associee un compte
            throw new PaymentAccountException("Aucun client associé à ce numéro de téléphone n'a été trouvé.");
        }

        // si le clientt existe il reste de Vérifier si le client a déjà un compte de paiement
        if (customer.getPaymentAccount() != null) {
            throw new PaymentAccountException("Le client a déjà un compte de paiement.");
        }

        // Sinon on lui Crée le compte de paiement pour le client
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setCustomer(customer);
        paymentAccount.setCreatedDate(LocalDate.now());
        paymentAccount.setAccountBalance(0.0);
        paymentAccount.setPhoneNumber(customer.getPhoneNumber());

        customer.setPaymentAccount(paymentAccount);

        return paymentAccountRepository.save(paymentAccount);
    }


    @Transactional
    public List<PaymentAccount> findAll() {
        return paymentAccountRepository.findAll();
    }

    @Transactional
    public PaymentAccount update(Long id, PaymentAccount updatedAccount) {
        Logger logger = LoggerFactory.getLogger(PaymentAccountService.class);
        Optional<PaymentAccount> paymentAccountOptional = paymentAccountRepository.findById(id);
        if (paymentAccountOptional.isPresent()) {
            PaymentAccount existingAccount = paymentAccountOptional.get();
            logger.info("Updating account: " + existingAccount);

            existingAccount.setAccountBalance(updatedAccount.getAccountBalance());
            existingAccount.setCreatedDate(updatedAccount.getCreatedDate());
            existingAccount.setBankName(updatedAccount.getBankName());
            existingAccount.setPhoneNumber(updatedAccount.getPhoneNumber());

            // Mise à jour des relations si nécessaire
            if (updatedAccount.getCustomer() != null) {
                Optional<Customer> customerOptional = customerRepository.findById(updatedAccount.getCustomer().getId());
                if (customerOptional.isPresent()) {
                    existingAccount.setCustomer(customerOptional.get());
                }
            }

            PaymentAccount savedAccount = paymentAccountRepository.save(existingAccount);
            logger.info("Updated account: " + savedAccount);
            return savedAccount;
        } else {
            throw new PaymentAccountException("Payment account with id " + id + " not found.");
        }
    }

    @Transactional
    public PaymentAccount delete(Long id) {
        Optional<PaymentAccount> paymentAccountOptional = paymentAccountRepository.findById(id);
        if (paymentAccountOptional.isPresent()) {
            PaymentAccount paymentAccount = paymentAccountOptional.get();

            // Supprimer le compte de paiement associé du client
            Customer customer = paymentAccount.getCustomer();
            customer.setPaymentAccount(null);
            customerRepository.save(customer);

            // Supprimer le compte de paiement
            paymentAccountRepository.delete(paymentAccount);

            return paymentAccount;
        } else {
            throw new PaymentAccountException("Payment account with id " + id + " not found.");
        }
    }

    @Transactional(readOnly = true)
    public double getAccountBalance(Long id) {
        Optional<PaymentAccount> paymentAccountOptional = paymentAccountRepository.findById(id);
        if (paymentAccountOptional.isPresent()) {
            return paymentAccountOptional.get().getAccountBalance();
        } else {
            throw new PaymentAccountException("Payment account with id " + id + " not found.");
        }
    }
}
