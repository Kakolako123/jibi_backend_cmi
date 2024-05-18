package com.example.jibi.service;

import com.example.jibi.model.PaymentAccount;

import java.util.List;
import java.util.Optional;

public interface PaymentAccountService {
    List<PaymentAccount> getAllPaymentAccounts();
    Optional<PaymentAccount> getPaymentAccountById(Long id);
    PaymentAccount createPaymentAccount(PaymentAccount paymentAccount);
    Optional<PaymentAccount> updatePaymentAccount(Long id, PaymentAccount paymentAccountDetails);
    void deletePaymentAccount(Long id);
    Optional<PaymentAccount> getPaymentAccountByPhoneNumber(String phoneNumber);
}
