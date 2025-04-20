package com.example.BEJobApplication.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // áp dụng cho tất cả các API
                        .allowedOrigins("*") // cho phép mọi domain truy cập
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // các HTTP method cho phép
                        .allowedHeaders("*"); // các header cho phép
            }
        };
    }
}
