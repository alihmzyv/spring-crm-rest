package com.example.web_customer_tracker.rest.controller;


import com.example.web_customer_tracker.entity.Customer;
import com.example.web_customer_tracker.exception.CustomExceptionHandler;
import com.example.web_customer_tracker.exception.CustomerNotFoundException;
import com.example.web_customer_tracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable long customerId) {
        return customerService.getCustomerById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String.format("Customer could not be found: id = %d", customerId)));
    }

    @PostMapping("/customers")
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomExceptionHandler.getErrorResponses(errors));
        }
        customer.setId(null);
        customerService.saveOrUpdateCustomer(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customer);
    }

    @PutMapping("/customers")
    public ResponseEntity updateCustomer(@RequestBody @Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomExceptionHandler.getErrorResponses(errors));
        }
        if (customer.getId() == null || customerService.getCustomerById(customer.getId()).isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer could not be found: id = %d", customer.getId()));
        }
        customerService.saveOrUpdateCustomer(customer);
        return ResponseEntity
                .ok(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.getCustomerById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String.format("Customer could not be found: id = %d", customerId)));
        customerService.deleteCustomerById(customerId);
    }
}
