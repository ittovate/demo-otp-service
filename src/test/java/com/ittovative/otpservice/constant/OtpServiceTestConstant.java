package com.ittovative.otpservice.constant;

import static com.ittovative.otpservice.constant.ExceptionConstant.UTILITY_CLASS_INSTANTIATION_MESSAGE;

public final class OtpServiceTestConstant {
    public static final String IMAGE_NAME = "redis";

    private OtpServiceTestConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION_MESSAGE);
    }
}
