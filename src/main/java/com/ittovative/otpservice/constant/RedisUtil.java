package com.ittovative.otpservice.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * The type Redis util.
 */
public class RedisUtil {
    /**
     * The constant VERIFICATION_OTP_HASH.
     */
    public static final String VERIFICATION_OTP_HASH = "VerificationOtp";

    /**
     * The constant EXPIRY_DATE_IN_MIN.
     */
    @Value("${spring.data.redis.expiry-date}")
    public static final Integer EXPIRY_DATE_IN_MIN = 5;
}
