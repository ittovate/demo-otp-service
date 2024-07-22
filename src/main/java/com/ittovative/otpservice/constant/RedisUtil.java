package com.ittovative.otpservice.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * The type Redis util.
 */
public final class RedisUtil {
    /**
     * The constant VERIFICATION_OTP_HASH.
     */
    public static final String VERIFICATION_OTP_HASH = "VerificationOtp";

    /**
     * Utility classes should not have public or default constructor (Sun Checks).
     */
    private RedisUtil() {
    }

    /**
     * The constant EXPIRY_DATE_IN_MIN.
     */
    @Value("${spring.data.redis.expiry-date}")
    public static final Integer EXPIRY_DATE_IN_MIN = 5;
}
