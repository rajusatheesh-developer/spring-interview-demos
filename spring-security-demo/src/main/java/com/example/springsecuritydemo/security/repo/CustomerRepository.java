package com.example.springsecuritydemo.security.repo;

import com.example.springsecuritydemo.security.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmail(String emil);
}
