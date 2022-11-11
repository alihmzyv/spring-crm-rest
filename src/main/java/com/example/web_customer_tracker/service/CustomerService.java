package com.example.web_customer_tracker.service;

import com.example.web_customer_tracker.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getCustomers();
    Optional<Customer> getCustomerById(Long id);
    void saveOrUpdateCustomer(Customer customer);
    void deleteCustomerById(Long id);
}
