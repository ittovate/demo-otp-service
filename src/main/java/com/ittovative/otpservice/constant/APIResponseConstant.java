package com.ittovative.otpservice.constant;

import static com.ittovative.otpservice.constant.ExceptionConstant.UTILITY_CLASS_INSTANTIATION;

public final class APIResponseConstant {
    public static final String OTP_SENT = "OTP sent successfully!";
    public static final String TOKEN_VERIFIED = "Token verified successfully!";

    private APIResponseConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION);
    }
}
