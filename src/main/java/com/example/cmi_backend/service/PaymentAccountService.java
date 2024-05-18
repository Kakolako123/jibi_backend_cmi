//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.cmi_backend.service;

import com.example.cmi_backend.dto.PaymentAccountCreationRequest;
import com.example.cmi_backend.entity.Customer;
import com.example.cmi_backend.entity.PaymentAccount;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.cmi_backend.repository.CustomerRepository;
import com.example.cmi_backend.repository.PaymentAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentAccountService {
    @Autowired
    private PaymentAccountRepository paymentAccountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public PaymentAccountService() {
    }

    public PaymentAccount createPaymentAccount(PaymentAccountCreationRequest request) {
        Logger logger = LoggerFactory.getLogger(PaymentAccountService.class);
        logger.info("Recherche du client avec le numéro de téléphone: " + request.getPhoneNumber());
        Optional<Customer> existingCustomer = this.customerRepository.findByPhoneNumber(request.getPhoneNumber());
        logger.info("Résultat de la recherche du client: " + String.valueOf(existingCustomer));
        if (existingCustomer.isPresent()) {
            Customer customer = (Customer)existingCustomer.get();
            if (customer.getPaymentAccount() != null) {
                throw new PaymentAccountException("Le client a déjà un compte de paiement.");
            } else {
                PaymentAccount paymentAccount = new PaymentAccount();
                paymentAccount.setCustomer(customer);
                paymentAccount.setCreatedDate(LocalDate.now());
                paymentAccount.setAccountBalance(0.0);
                paymentAccount.setPhoneNumber(customer.getPhoneNumber());
                customer.setPaymentAccount(paymentAccount);
                return (PaymentAccount)this.paymentAccountRepository.save(paymentAccount);
            }
        } else {
            throw new PaymentAccountException("Aucun client associé à ce numéro de téléphone n'a été trouvé.");
        }
    }

    @Transactional
    public List<PaymentAccount> findAll() {
        return this.paymentAccountRepository.findAll();
    }

    @Transactional
    public PaymentAccount update(Long id, PaymentAccount updatedAccount) {
        Logger logger = LoggerFactory.getLogger(PaymentAccountService.class);
        Optional<PaymentAccount> paymentAccountOptional = this.paymentAccountRepository.findById(id);
        if (paymentAccountOptional.isPresent()) {
            PaymentAccount existingAccount = (PaymentAccount)paymentAccountOptional.get();
            logger.info("Updating account: " + String.valueOf(existingAccount));
            existingAccount.setAccountBalance(updatedAccount.getAccountBalance());
            existingAccount.setCreatedDate(updatedAccount.getCreatedDate());
            existingAccount.setBankName(updatedAccount.getBankName());
            existingAccount.setPhoneNumber(updatedAccount.getPhoneNumber());
            if (updatedAccount.getCustomer() != null) {
                Optional<Customer> customerOptional = this.customerRepository.findById(updatedAccount.getCustomer().getId());
                if (customerOptional.isPresent()) {
                    existingAccount.setCustomer((Customer)customerOptional.get());
                }
            }

            PaymentAccount savedAccount = (PaymentAccount)this.paymentAccountRepository.save(existingAccount);
            logger.info("Updated account: " + String.valueOf(savedAccount));
            return savedAccount;
        } else {
            throw new PaymentAccountException("Payment account with id " + id + " not found.");
        }
    }

    @Transactional
    public PaymentAccount delete(Long id) {
        Optional<PaymentAccount> paymentAccountOptional = this.paymentAccountRepository.findById(id);
        if (paymentAccountOptional.isPresent()) {
            PaymentAccount paymentAccount = (PaymentAccount)paymentAccountOptional.get();
            Customer customer = paymentAccount.getCustomer();
            customer.setPaymentAccount((PaymentAccount)null);
            this.customerRepository.save(customer);
            this.paymentAccountRepository.delete(paymentAccount);
            return paymentAccount;
        } else {
            throw new PaymentAccountException("Payment account with id " + id + " not found.");
        }
    }

    @Transactional(
            readOnly = true
    )
    public double getAccountBalance(Long id) {
        Optional<PaymentAccount> paymentAccountOptional = this.paymentAccountRepository.findById(id);
        if (paymentAccountOptional.isPresent()) {
            return ((PaymentAccount)paymentAccountOptional.get()).getAccountBalance();
        } else {
            throw new PaymentAccountException("Payment account with id " + id + " not found.");
        }
    }
}
