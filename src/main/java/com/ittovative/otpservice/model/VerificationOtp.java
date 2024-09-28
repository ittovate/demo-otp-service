package com.ittovative.otpservice.model;

import com.ittovative.otpservice.constant.RedisConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.redis.core.RedisHash;

import static com.ittovative.otpservice.constant.ApiResponseConstant.INVALID_PHONE_NUMBER_FORMAT;

@RedisHash(value = RedisConstant.VERIFICATION_TOKEN_HASH)
public record VerificationOtp(
        @NotBlank @Pattern(regexp = "^[+0-9-]+$", message = INVALID_PHONE_NUMBER_FORMAT) String phoneNumber,
        String token) {
}
