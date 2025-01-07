package com.example.showTimeService.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin");
    }
}
*/

/*
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;
/*

@Configuration
public class FeignClientConfig {

    // Feign Request Interceptor for Logging or Dynamic Headers
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // For debugging or adding global headers
                System.out.println("Request to: " + template.url());
            }
        };
<<<<<<< HEAD
    }
=======
    }*/

/*
@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Retrieve the JWT token from the SecurityContext
            String jwtToken = getJwtToken();
            if (jwtToken != null) {
                requestTemplate.header("Authorization", "Bearer " + jwtToken);
            }
        };
    }

    private String getJwtToken() {
        // Retrieve the JWT token from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials();
        }
        return null; // Return null if no JWT token is found
    }
}
*/
