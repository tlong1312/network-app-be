package com.mhpl.network_app_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenFilter jwtAuthenFilter;

    public SecurityConfig(JwtAuthenFilter jwtAuthenFilter) {
        this.jwtAuthenFilter = jwtAuthenFilter;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login", "/api/users/**").permitAll()
                        .requestMatchers("/api/auth/posts").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)  throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
