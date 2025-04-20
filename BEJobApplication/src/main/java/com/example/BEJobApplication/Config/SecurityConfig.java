package com.example.BEJobApplication.Config;

import com.example.BEJobApplication.Utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import com.example.BEJobApplication.Entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINT = {
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/logingoogle",

    };
    private final JwtUtils jwtUtils; // Inject JwtUtils

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService,JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
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

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Cấu hình stateless
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/api-docs",
                                "/api-docs/swagger-config",
                                "/api-docs/**"
                        ).permitAll()
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()

//                .authorizeHttpRequests(request -> request
//                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()//khong cần login
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // bắt buộc login
//                        .requestMatchers("/admin/**").hasRole("ADMIN")// chỉ có admin mới  vào được
//                        .anyRequest().permitAll() // tất cả API đều cho phép truy cập

                )
                .authenticationProvider(authenticationProvider());
        http.addFilterBefore(
                new JWTAuthenticationFilter(jwtUtils, userDetailsService),
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }


}
