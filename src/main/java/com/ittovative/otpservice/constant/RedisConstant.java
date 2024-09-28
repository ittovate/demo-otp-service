package com.ittovative.otpservice.constant;

import org.springframework.beans.factory.annotation.Value;

public abstract class RedisConstant {
    public static final String VERIFICATION_TOKEN_HASH = "VerificationToken";

    @Value("${spring.data.redis.expiry-date}")
    public static final Integer EXPIRY_DATE_IN_MIN = 5;
}
