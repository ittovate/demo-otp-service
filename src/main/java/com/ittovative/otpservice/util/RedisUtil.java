package com.ittovative.otpservice.util;

import org.springframework.beans.factory.annotation.Value;

public class RedisUtil {
    public static final String VERIFICATION_OTP_HASH = "VerificationOtp";


    @Value("${spring.data.redis.expiry-date}")
    public static Integer EXPIRY_DATE;

}
