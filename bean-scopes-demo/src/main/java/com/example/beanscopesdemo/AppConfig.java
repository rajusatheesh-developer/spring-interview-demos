package com.example.beanscopesdemo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@Configuration
public class AppConfig {

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SingletonScope singletonScope() {
        return new SingletonScope();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeScope prototypeScope() {
        return new PrototypeScope();
    }


    @Bean
 //   @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
    @RequestScope
    public RequestScopeClass requestScope() {

        return new RequestScopeClass("One", LocalDateTime.now());
    }
}
