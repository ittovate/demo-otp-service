package com.ittovative.otpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis config.
 */
@Configuration
public class RedisConfig {
    /**
     * Redis template.
     *
     * @param connectionFactory the connection factory
     * @return the redis template
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(final RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
