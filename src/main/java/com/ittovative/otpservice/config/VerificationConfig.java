package com.ittovative.otpservice.config;

import com.ittovative.otpservice.service.InMemoryVerificationService;
import com.ittovative.otpservice.service.VerificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class VerificationConfig {
    @Bean
    public VerificationService verificationService(){
        return new InMemoryVerificationService(new HashMap<>());
    }
}
