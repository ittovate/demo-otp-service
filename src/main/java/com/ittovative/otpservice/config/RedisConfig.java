package com.ittovative.otpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    /**
     * Creates and configures a {@link RedisTemplate} for interacting with Redis.
     * <p>
     * This template provides a high-level abstraction for Redis operations using
     * the given {@link RedisConnectionFactory} to establish the connection.
     *
     * @param connectionFactory the Redis connection factory used to establish the connection
     * @return a configured {@link RedisTemplate} for Redis operations
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
