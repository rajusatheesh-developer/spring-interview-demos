package com.example.springsecuritydemo.security.provider;

import com.example.springsecuritydemo.security.entity.Customer;
import com.example.springsecuritydemo.security.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomUsernameAndPasswordAuthProvider implements AuthenticationProvider {


    @Autowired
    private CustomerRepository customerRepository;


    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(userName);
        if (Objects.nonNull(customer)) {
            if (passwordEncoder.matches(pwd, customer.getPassword())) {
                List<SimpleGrantedAuthority> authorities = customer.getRoles()
                        .stream().map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
                return new UsernamePasswordAuthenticationToken(userName, pwd, authorities);
            } else
                throw new BadCredentialsException("No user found with " + userName);
        } else {
            throw new BadCredentialsException("No user found with " + userName);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
