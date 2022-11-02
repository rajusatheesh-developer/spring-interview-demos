package com.example.springsecuritydemo.security.manager;

import com.example.springsecuritydemo.security.details.CustomerDetails;
import com.example.springsecuritydemo.security.entity.Customer;
import com.example.springsecuritydemo.security.repo.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class CustomerDetailsManager implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);
        if (Objects.isNull(customer)) {
            throw new UsernameNotFoundException("Customer not found with email :" + username);
        }
        return new CustomerDetails(customer);
    }


    public void createUser(Customer customer) {
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    public void updateUser(UserDetails user) {

    }


    public void deleteUser(String username) {

    }

    public void changePassword(String oldPassword, String newPassword) {

    }


    public boolean userExists(String username) {
        return false;
    }

    public List<Customer> getAll()
    {
      return (List<Customer>) customerRepository.findAll();
    }
}
