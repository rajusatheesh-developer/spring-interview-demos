package com.example.springbatchdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by Satheesh Raju on 07-10-2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    private String companyName;
    private String address;
    private String city;
    private String country;
    private String state;
    private String zip;
    private String phone1;
    private String phone2;
    private String email;
    private String web;


}
