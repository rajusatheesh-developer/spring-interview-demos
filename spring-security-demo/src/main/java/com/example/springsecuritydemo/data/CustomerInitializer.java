package com.example.springsecuritydemo.data;

import com.example.springsecuritydemo.security.entity.Customer;
import com.example.springsecuritydemo.security.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerInitializer implements ApplicationRunner {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerRepository.deleteAll();
        Customer customer = Customer.builder()
                .email("satheesh@gmail.com")
                .password("12345")
                .roles(List.of("admin"))
                .build();

        customerRepository.save(customer);


    }
}
