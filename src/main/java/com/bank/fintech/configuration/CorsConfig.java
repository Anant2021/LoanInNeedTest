package com.bank.fintech.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000") // Allow requests from your React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");

                registry.addMapping("/api/**")
                        .allowedOrigins("http://192.168.1.10:3000") // Allow requests from your React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");


                        registry.addMapping("/api/**")
                                .allowedOrigins("http://loaninneedtest-production.up.railway.app") // Allow requests from your React app
                                .allowedMethods("GET", "POST", "PUT", "DELETE")
                                .allowedHeaders("*");


            }
        };
    }
}