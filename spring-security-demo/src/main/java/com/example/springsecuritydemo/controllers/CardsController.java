package com.example.springsecuritydemo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping(value = "/cards")
    public String getCards() {
        return "Welcome to cards";
    }


}
