package com.example.springbatchdemo.repo;

import com.example.springbatchdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by Satheesh Raju on 07-10-2022
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{

}
