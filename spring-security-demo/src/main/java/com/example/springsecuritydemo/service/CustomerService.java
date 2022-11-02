package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.security.entity.Customer;
import com.example.springsecuritydemo.security.manager.CustomerDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDetailsManager customerDetailsManager;

    public CustomerService(CustomerDetailsManager customerDetailsManager) {
        this.customerDetailsManager = customerDetailsManager;
    }

    public Customer signup(Customer customer) {
        customerDetailsManager.createUser(customer);
        return customer;
    }

    public List<Customer> getAll() {
        return customerDetailsManager.getAll();
    }
}
