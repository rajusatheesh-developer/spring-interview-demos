package com.example.springsecuritydemo.mappers;

import com.example.springsecuritydemo.security.entity.Customer;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerMapper
{
    public static UserDetails mapFrom(Customer customer)
    {
        return (UserDetails) customer;
    }
}
