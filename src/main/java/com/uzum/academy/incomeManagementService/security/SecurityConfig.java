package com.uzum.academy.incomeManagementService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/", "/auth/**").permitAll()
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
