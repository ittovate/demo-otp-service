package com.ittovative.otpservice.model;

import com.ittovative.otpservice.constant.RedisUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.redis.core.RedisHash;


/**
 * The type Verification otp.
 *
 * @param phoneNumber the phone number
 * @param token       the token
 */
@RedisHash(value = RedisUtil.VERIFICATION_OTP_HASH)
public record VerificationOtp(
        @NotBlank
        @Pattern(regexp = "^[+0-9-]+$",
                message = "Invalid phone number format(Only digits, plus-sign and dashes are allowed)")
        String phoneNumber,
        String token) {
}
