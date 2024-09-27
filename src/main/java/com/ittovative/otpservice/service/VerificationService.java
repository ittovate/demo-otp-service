package com.ittovative.otpservice.service;

import com.ittovative.otpservice.constant.RedisConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.apache.coyote.BadRequestException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.ittovative.otpservice.constant.ExceptionConstant.INVALID_TOKEN;
import static com.ittovative.otpservice.constant.ExceptionConstant.INVALID_TOKEN_FORMAT;
import static com.ittovative.otpservice.constant.ExceptionConstant.TOKEN_EXPIRED;

@Service
public class VerificationService {
    private final RedisTemplate<String, Object> redisTemplate;


    public VerificationService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Set user token.
     *
     * @param userPhone the user phone
     * @param token     the token
     */
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
        public void validateUserToken(String userPhone, String receivedToken)
            throws BadRequestException {

        @NotBlank @Pattern(regexp = "^\\d{6}$", message = INVALID_TOKEN_FORMAT)
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
