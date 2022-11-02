package com.example.springsecuritydemo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping(value = "/notices")
    public String getNotices() {
        return "Welcome to notices";
    }


}
