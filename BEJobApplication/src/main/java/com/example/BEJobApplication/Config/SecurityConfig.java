package com.example.BEJobApplication.Config;

import org.springframework.context.annotation.Bean;
import com.example.BEJobApplication.Entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINT = {
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/logingoogle",
            "/api/Job/GetAllJob",
    };

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // không dùng new, dùng injected bean
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()//khong cần login
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // bắt buộc login
                        .requestMatchers("/admin/**").hasRole("ADMIN")// chỉ có admin mới  vào được
                        .anyRequest().permitAll() // tất cả API đều cho phép truy cập
                )
                .authenticationProvider(authenticationProvider())
                .formLogin(login -> login.disable()); // không dùng form login mặc định

        return http.build();
    }
}
