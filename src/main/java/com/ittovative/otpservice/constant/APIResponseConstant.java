package com.ittovative.otpservice.constant;

import static com.ittovative.otpservice.constant.ExceptionConstant.UTILITY_CLASS_INSTANTIATION_MESSAGE;

public final class APIResponseConstant {
    public static final String OTP_SENT_MESSAGE = "OTP sent successfully!";
    public static final String TOKEN_VERIFIED_MESSAGE = "Token verified successfully!";

    private APIResponseConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION_MESSAGE);
    }
}
