package com.ittovative.otpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CollectionConfig {
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
