package com.banking.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsConfig {

    // Register the CORS configuration as a Spring bean
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                // Allow CORS requests for all payment API endpoints
                CorsRegistration corsRegistration = registry.addMapping("/api/**")
                        // Allow all request headers
                        .allowedHeaders("*")
                        // Allow the supported HTTP methods
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
