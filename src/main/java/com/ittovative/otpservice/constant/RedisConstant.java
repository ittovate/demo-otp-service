package com.ittovative.otpservice.constant;

import org.springframework.beans.factory.annotation.Value;

import static com.ittovative.otpservice.constant.ExceptionConstant.UTILITY_CLASS_INSTANTIATION;

public final class RedisConstant {
    public static final String VERIFICATION_TOKEN_HASH = "VerificationToken";

    @Value("${spring.data.redis.expiry-date}")
    public static final Integer EXPIRY_DATE_IN_MIN = 5;

    private RedisConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION);
    }
}
