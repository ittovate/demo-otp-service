package com.ittovative.otpservice.service;

import com.ittovative.otpservice.constant.RedisUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


/**
 * The type Redis verification service.
 */
@Service
@Primary
public class RedisVerificationService implements VerificationService {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Instantiates a new Redis verification service.
     *
     * @param redisTemplate the redis template
     */
    public RedisVerificationService(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Set user token.
     *
     * @param userPhone the user phone
     * @param token     the token
     */
    @Override
    public void setUserToken(final String userPhone, final String token) {
        redisTemplate.opsForValue().set(userPhone, token);
        redisTemplate.expire(userPhone, RedisUtil.EXPIRY_DATE_IN_MIN, TimeUnit.MINUTES);
    }

    /**
     * Set user token.
     *
     * @param userPhone     the user phone
     * @param receivedToken the received token
     */
    @Override
    public void validateUserToken(final String userPhone, final String receivedToken)
            throws BadRequestException {
        String actualToken = (String) redisTemplate.opsForValue().get(userPhone);
        if (actualToken == null) {
            throw new NoSuchElementException("This phone did receive a token before or it got expired!");
        }
        if (!actualToken.equals(receivedToken)) {
            throw new BadRequestException("Invalid token!");
        }
        redisTemplate.opsForValue().getAndDelete(userPhone);
    }
}
