package com.example.springsecuritydemo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping(value = "/accounts")
    public String getAccount() {
        return "Welcome to account";
    }


}
