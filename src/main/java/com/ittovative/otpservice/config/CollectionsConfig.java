package com.ittovative.otpservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Collections config.
 */
@Configuration
public class CollectionsConfig {
    /**
     * Map bean.
     *
     * @return the map
     */
    @Bean
    @Scope("prototype")
    public Map<String, String> hashMapBean() {
        return new HashMap<>();
    }
}
