package com.ittovative.otpservice.constant;

import org.springframework.beans.factory.annotation.Value;

public final class RedisUtil {
    public static final String VERIFICATION_OTP_HASH = "VerificationOtp";

    private RedisUtil() {
    }

    @Value("${spring.data.redis.expiry-date}")
    public static final Integer EXPIRY_DATE_IN_MIN = 5;
}
