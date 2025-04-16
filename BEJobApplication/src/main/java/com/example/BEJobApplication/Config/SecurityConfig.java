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
            "/api/User/GetAllUser",

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/api-docs",
                                "/api-docs/swagger-config" ,
                                "/api-docs/**"
                        ).permitAll()
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Bảo vệ admin endpoints
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Bảo vệ user endpoints
                        .anyRequest().authenticated() // Các yêu cầu còn lại phải xác thực
                )
                .formLogin(form -> form
                        .loginPage("/login")   // Chỉ định trang login tuỳ chỉnh (nếu có)
                        .permitAll()           // Cho phép tất cả truy cập trang login
                );

        return http.build();
    }


}
