package com.jwlimpafossa.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desabilita proteção CSRF (padrão para APIs REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Libera todos os endpoints que começam com /api
                        .anyRequest().authenticated() // O resto continua protegido
                );

        return http.build();
    }
}
