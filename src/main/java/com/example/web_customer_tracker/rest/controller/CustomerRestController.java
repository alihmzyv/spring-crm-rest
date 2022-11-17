package com.example.web_customer_tracker.rest.controller;


import com.example.web_customer_tracker.entity.Customer;
import com.example.web_customer_tracker.exception.CustomerNotFoundException;
import com.example.web_customer_tracker.service.CustomerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class CustomerRestController {
    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public Page<Customer> getCustomers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "3") int size,
                                       @RequestParam(defaultValue = "") List<String> sort) {
        return customerService.findAll(page, size, sort);
    }

    @GetMapping("/customers/{customerId}")
    @SneakyThrows
    public Customer getCustomer(@PathVariable long customerId) {
        return customerService.getCustomerById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String.format("Customer could not be found: id = %d", customerId)));
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
        customerService.saveOrUpdateCustomer(customer);
        return customer;
    }

    @PutMapping("/customers/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody @Valid Customer customer) {
        if (customerService.getCustomerById(customerId).isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer could not be found: id = %d", customer.getId()));
        }
        customer.setId(customerId);
        customerService.saveOrUpdateCustomer(customer);
        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.getCustomerById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String.format("Customer could not be found: id = %d", customerId)));
        customerService.deleteCustomerById(customerId);
    }
}
