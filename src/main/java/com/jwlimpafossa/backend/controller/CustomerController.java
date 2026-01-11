package com.jwlimpafossa.backend.controller;


import com.jwlimpafossa.backend.domain.Customer;
import com.jwlimpafossa.backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repository;

    @GetMapping
    public List<Customer> listAllCustomers() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }
}
