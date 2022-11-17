package com.example.web_customer_tracker.service;

import com.example.web_customer_tracker.entity.Customer;
import com.example.web_customer_tracker.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepo;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public Page<Customer> findAll(int page, int size, List<String> sort) {
        return customerRepo.findAll(PageRequest.of(page, size, Sort.by(createSortOrder(sort))));
    }

    @Override
    public void saveOrUpdateCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepo.deleteById(id);
    }


    private List<Sort.Order> createSortOrder(List<String> sort) {
        System.out.println(sort);
        if (sort.size() == 2
                && (sort.get(1).equalsIgnoreCase("asc") || sort.get(1).equalsIgnoreCase("desc"))) {
            return List.of(new Sort.Order(Sort.Direction.fromString(sort.get(1)), sort.get(0)));
        }
        return sort.stream()
                .map(sortAndOrder -> sortAndOrder.split(","))
                .map(sortAndOrder -> {
                    if (sortAndOrder.length == 1) {
                        return new Sort.Order(Sort.Direction.ASC, sortAndOrder[0]);
                    }
                    return new Sort.Order(Sort.Direction.fromString(sortAndOrder[1]), sortAndOrder[0]);
                })
                .toList();
    }
}
