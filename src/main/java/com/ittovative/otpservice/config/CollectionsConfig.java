package com.ittovative.otpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

@Configuration
public class CollectionsConfig {
    @Bean
    @Scope("prototype")
    public HashMap<String,String> hashMapBean(){
        return new HashMap<>();
    }
}
