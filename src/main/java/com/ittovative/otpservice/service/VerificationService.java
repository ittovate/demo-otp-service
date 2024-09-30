package com.ittovative.otpservice.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.apache.coyote.BadRequestException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.ittovative.otpservice.constant.ExceptionConstant.INVALID_TOKEN_FORMAT_MESSAGE;
import static com.ittovative.otpservice.constant.ExceptionConstant.INVALID_TOKEN_MESSAGE;
import static com.ittovative.otpservice.constant.ExceptionConstant.TOKEN_EXPIRED_MESSAGE;
import static com.ittovative.otpservice.constant.RedisConstant.EXPIRY_DATE_IN_MIN;

@Service
public class VerificationService {
    private final RedisTemplate<String, Object> redisTemplate;

    public VerificationService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Stores the OTP token associated with the user's phone number in Redis with an expiration time.
     * <p>
     * This method saves the OTP token in Redis for the specified user's phone number. The token will
     * expire after a predefined time limit, which is set using the {@code RedisConstant.EXPIRY_DATE_IN_MIN}.
     * This ensures that the OTP is valid only for a certain period.
     * </p>
     *
     * @param userPhone The phone number of the user to associate with the OTP token.
     * @param token     The OTP token to be stored in Redis.
     */

    public void setUserToken(String userPhone, String token) {
        redisTemplate.opsForValue().set(userPhone, token);
        redisTemplate.expire(userPhone, EXPIRY_DATE_IN_MIN, TimeUnit.MINUTES);
    }

    /**
     * Validates the OTP token provided by the user against the stored token in Redis.
     * <p>
     * This method retrieves the OTP token stored in Redis for the specified user's phone number and compares
     * it with the token provided by the user. If the token has expired (i.e., no token is found in Redis),
     * or if the provided token does not match the stored one, an appropriate exception is thrown.
     * Once validated, the token is deleted from Redis.
     * </p>
     *
     * @param userPhone     The phone number of the user.
     * @param receivedToken The OTP token provided by the user for verification.
     * @throws NoSuchElementException If the OTP token has expired or is not found.
     * @throws BadRequestException    If the provided OTP token is invalid or does not match the stored token.
     */
    public void validateUserToken(String userPhone, String receivedToken) throws BadRequestException {

        @NotBlank @Pattern(regexp = "^\\d{6}$", message = INVALID_TOKEN_FORMAT_MESSAGE)
        String actualToken = (String) redisTemplate.opsForValue().get(userPhone);

        if (actualToken == null) {
            throw new NoSuchElementException(TOKEN_EXPIRED_MESSAGE);
        }
        if (!actualToken.equals(receivedToken)) {
            throw new BadRequestException(INVALID_TOKEN_MESSAGE);
        }

        redisTemplate.opsForValue().getAndDelete(userPhone);
    }
}
