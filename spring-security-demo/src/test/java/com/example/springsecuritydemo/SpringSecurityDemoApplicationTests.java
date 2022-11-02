package com.example.springsecuritydemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

    @Autowired
    private  AuthenticationManager authenticationManager;
    @Disabled
    @Test
    void contextLoads() {
    }


    @Disabled
    @Test
    public void expectOKResponse_WhenAuthenticaionManagerIsTestedWithCorrectDetails() {

        Authentication authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken.unauthenticated("user", "password"));

        Assertions.assertThat(authentication.isAuthenticated()).isTrue();
    }
}
