package com.ittovative.otpservice.constant;

import static com.ittovative.otpservice.constant.ExceptionConstant.UTILITY_CLASS_INSTANTIATION_MESSAGE;

public final class ServiceConstant {
    public static final int RANDOM_UPPER_LIMIT = 900_000;
    public static final int RANDOM_LOWER_LIMIT = 100_000;
    public static final String OTP_SMS_MESSAGE = "Here is your OTP: %s";

    private ServiceConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION_MESSAGE);
    }
}
