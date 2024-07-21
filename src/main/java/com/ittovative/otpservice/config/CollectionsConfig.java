package com.ittovative.otpservice.config;

import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CollectionsConfig {
  @Bean
  @Scope("prototype")
  public HashMap<String, String> hashMapBean() {
    return new HashMap<>();
  }
}
