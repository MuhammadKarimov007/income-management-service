package com.uzum.academy.incomeManagementService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/",
                                        "/auth/**",
                                        "/static/**",
                                        "https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js",
                                        "/images/**",
                                        "/styles/index-style.css").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form ->form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/processLogin")
                        .defaultSuccessUrl("/app/main", true)
                        .failureUrl("/auth/login?failed"))
                .build();
    }

}
