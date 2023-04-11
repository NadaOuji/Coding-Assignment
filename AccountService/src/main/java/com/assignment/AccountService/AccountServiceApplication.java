package com.assignment.AccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class AccountServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AccountServiceApplication.class, args);
    }
/**
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
            .build();
    }**/


}
