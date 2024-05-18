package com.example.jibi.service;

import com.example.jibi.entity.Customer;
import com.example.jibi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setAddress(customerDetails.getAddress());
            customer.setCin(customerDetails.getCin());
            customer.setBirthdate(customerDetails.getBirthdate());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setPassword(customerDetails.getPassword());
            customer.setCreatedDate(customerDetails.getCreatedDate());
            customer.setCommercialRn(customerDetails.getCommercialRn());
            customer.setPatent(customerDetails.getPatent());
            return customerRepository.save(customer);
        });
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
