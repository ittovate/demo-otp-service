package com.ittovative.otpservice.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Collections config.
 */
@Configuration
public class CollectionsConfig {
    /**
     * Hash map bean hash map.
     *
     * @return the hash map
     */
    @Bean
    @Scope("prototype")
    public HashMap<String, String> hashMapBean() {
        return new HashMap<>();
    }
}
