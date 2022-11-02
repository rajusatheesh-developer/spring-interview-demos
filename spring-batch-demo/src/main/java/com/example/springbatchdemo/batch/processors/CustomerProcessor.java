package com.example.springbatchdemo.batch.processors;

import com.example.springbatchdemo.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

/**
 * created by Satheesh Raju on 08-10-2022
 */
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        return customer;
    }
}
