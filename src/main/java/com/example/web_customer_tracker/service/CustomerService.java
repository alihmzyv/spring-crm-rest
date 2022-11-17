package com.example.web_customer_tracker.service;

import com.example.web_customer_tracker.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Page<Customer> findAll(int page, int size, List<String> sortAndOrder);

    Optional<Customer> getCustomerById(Long id);

    void saveOrUpdateCustomer(Customer customer);

    void deleteCustomerById(Long id);
}