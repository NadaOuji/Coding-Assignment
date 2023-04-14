package com.assignment.CustomerService.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.admin.role}")
    private String adminRole;

    @Value("${security.user.role}")
    private String userRole;

    @Value("${security.user.name}")
    private String userName;

    @Value("${security.admin.url}")
    private String adminUrl;

    @Value("${security.admin.name}")
    private String adminName;

    @Value("${security.user.url}")
    private String userUrl;

    @Value("${security.swagger.ui.url}")
    private String swaggerUiUrl;

    @Value("${security.swagger.resources.url}")
    private String swaggerResourcesUrl;

    @Value("${security.api.docs.url}")
    private String apiDocsUrl;

    @Value("${security.password}")
    private String password;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers((request) -> request.getRequestURI().startsWith(adminUrl)).hasRole(adminRole)
                .requestMatchers((request) -> request.getRequestURI().startsWith(userUrl)).hasRole(userRole)
                .requestMatchers(request -> request.getRequestURI().startsWith(swaggerUiUrl)).permitAll()
                .requestMatchers(request -> request.getRequestURI().startsWith(swaggerResourcesUrl)).permitAll()
                .requestMatchers(request -> request.getRequestURI().startsWith(apiDocsUrl)).permitAll()
                .and().formLogin();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        logger.debug(encoder.encode(password));

        UserDetails user = User.withUsername(userName)
                .password(encoder.encode(password))
                .roles(userRole)
                .build();
        UserDetails admin = User.withUsername(adminName)
                .password(encoder.encode(password))
                .roles(adminRole, userRole)
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
