package com.example.cmi_backend.service;

public class PaymentAccountException extends RuntimeException {
    public PaymentAccountException(String message) {
        super(message);
    }
}