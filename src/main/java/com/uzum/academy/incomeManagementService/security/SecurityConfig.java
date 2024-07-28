package com.uzum.academy.incomeManagementService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(config ->
                config
                        .requestMatchers("/login",
                                "/sign-in",
                                "/register",
                                "/resources/**",
                                "/static/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "http://www.thymeleaf.org",
                                "https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css",
                                "https://kit.fontawesome.com/7433d3320f.js",
                                "https://facebook.com",
                                "https://x.com",
                                "https://instagram.com",
                                "https://github.com/MuhammadKarimov007",
                                "https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js").permitAll()
                        .anyRequest().authenticated()
        )
                .formLogin(form ->
                        form
                                .loginPage("/sign-in")
                                .loginProcessingUrl("/processClient")
                                .permitAll()
                );

        return http.build();
    }
}
