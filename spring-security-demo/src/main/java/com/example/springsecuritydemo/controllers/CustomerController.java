package com.example.springsecuritydemo.controllers;


import com.example.springsecuritydemo.security.entity.Customer;
import com.example.springsecuritydemo.security.manager.CustomerDetailsManager;
import com.example.springsecuritydemo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/signup")
    public Customer signup(@RequestBody Customer customer) {
        return customerService.signup(customer);
    }

    @GetMapping(value = "/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

}
