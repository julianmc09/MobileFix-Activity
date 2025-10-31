package com.example.CRUD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disabling CSRF for simplicity, will enable later
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(toH2Console()).permitAll()
                // Repair Orders
                .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("ADMIN", "TECH", "USER")
                .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/orders/{id}/assign/{techId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/orders/{id}/status").hasAnyRole("TECH", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/orders/{id}").hasAnyRole("USER", "ADMIN")
                // Devices
                .requestMatchers(HttpMethod.GET, "/api/devices").authenticated()
                .requestMatchers("/api/devices/**").hasRole("ADMIN")
                // Users
                .requestMatchers("/api/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // For H2 console
            .formLogin(form -> form.permitAll())
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("user123"))
            .roles("USER")
            .build();

        UserDetails tech = User.builder()
            .username("tech")
            .password(passwordEncoder().encode("tech123"))
            .roles("TECH")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, tech, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
