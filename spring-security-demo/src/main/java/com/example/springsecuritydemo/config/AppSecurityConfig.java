package com.example.springsecuritydemo.config;

import ch.qos.logback.core.joran.action.NOPAction;
import com.example.springsecuritydemo.security.provider.CustomUsernameAndPasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUsernameAndPasswordAuthProvider provider;



    //customized security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests((requests) -> {
            requests.antMatchers("/accounts")
                    .authenticated();
            requests.antMatchers("/balance").authenticated();
            requests.antMatchers("/loans").authenticated();
            requests.antMatchers("/cards").authenticated();
            requests.antMatchers("/contacts").permitAll();
            requests.antMatchers("/notices").permitAll();
            requests.antMatchers("/signup").permitAll();
            //   requests.antMatchers("/customers/*").authenticated();

        });
        http.formLogin(); // Browser based form login
        http.httpBasic(); // Calling from  PostMan, RestTemplate,Angular
    }

    // InMemory User Authentication
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("12345").authorities("admin").and()
//                .withUser("user").password("12345").authorities("readonly").and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    // InMemory User Authentication
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
//        UserDetails user = User.withUsername("user").password("12345").authorities("readonly").build();
//
//        manager.createUser(admin);
//        manager.createUser(user);
//
//        auth.userDetailsService(manager);
//    }


    //JdbcUserDetailsManager
//    @Bean
//    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    //denyAll requests security
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests) -> {
//            requests.anyRequest().denyAll();
//
//        });
//        http.formLogin(); // Browser based form login
//        http.httpBasic(); // Calling from  PostMan, RestTemplate,Angular
//    }

//    //permitAll requests security
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests) -> {
//            requests.anyRequest().permitAll();
//
//        });
//        http.formLogin(); // Browser based form login
//        http.httpBasic(); // Calling from  PostMan, RestTemplate,Angular
//    }

    // Custom Authentication Provider
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }
}
