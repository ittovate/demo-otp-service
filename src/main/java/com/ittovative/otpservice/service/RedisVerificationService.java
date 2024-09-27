package com.ittovative.otpservice.service;

import com.ittovative.otpservice.constant.RedisConstant;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.ittovative.otpservice.constant.ApiResponseConstant.INVALID_TOKEN;
import static com.ittovative.otpservice.constant.ApiResponseConstant.TOKEN_EXPIRED;

@Service
@Primary
public class RedisVerificationService implements VerificationService {
    private final RedisTemplate<String, Object> redisTemplate;


    public RedisVerificationService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Set user token.
     *
     * @param userPhone the user phone
     * @param token     the token
     */
    @Override
    public void setUserToken(String userPhone, String token) {
        redisTemplate.opsForValue().set(userPhone, token);
        redisTemplate.expire(userPhone, RedisConstant.EXPIRY_DATE_IN_MIN, TimeUnit.MINUTES);
    }

    /**
     * Set user token.
     *
     * @param userPhone     the user phone
     * @param receivedToken the received token
     */
    @Override
    public void validateUserToken(String userPhone, String receivedToken)
            throws BadRequestException {
        String actualToken = (String) redisTemplate.opsForValue().get(userPhone);
        if (actualToken == null) {
            throw new NoSuchElementException(TOKEN_EXPIRED);
        }
        if (!actualToken.equals(receivedToken)) {
            throw new BadRequestException(INVALID_TOKEN);
        }
        redisTemplate.opsForValue().getAndDelete(userPhone);
    }
}
