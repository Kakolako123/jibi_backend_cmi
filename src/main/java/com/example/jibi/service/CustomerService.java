package com.example.jibi.service;

import com.example.jibi.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Optional<Customer> updateCustomer(Long id, Customer customerDetails);
    void deleteCustomer(Long id);
}
