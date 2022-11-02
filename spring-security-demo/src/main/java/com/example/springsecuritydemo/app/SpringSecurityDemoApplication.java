package com.example.springsecuritydemo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("com.example.springsecuritydemo.controllers"),
        @ComponentScan("com.example.springsecuritydemo.config"),
        @ComponentScan("com.example.springsecuritydemo.security.manager"),
        @ComponentScan("com.example.springsecuritydemo.security.provider"),
        @ComponentScan("com.example.springsecuritydemo.service")
})
@EnableJpaRepositories(value = {
        "com.example.springsecuritydemo.security.repo"
})
@EntityScan(value = {
        "com.example.springsecuritydemo.security.entity"
})
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }


}
