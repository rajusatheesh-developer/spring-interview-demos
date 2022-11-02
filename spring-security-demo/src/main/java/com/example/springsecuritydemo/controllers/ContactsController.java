package com.example.springsecuritydemo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsController {

    @GetMapping(value = "/contacts")
    public String getContacts() {
        return "Welcome to contacts";
    }


}
