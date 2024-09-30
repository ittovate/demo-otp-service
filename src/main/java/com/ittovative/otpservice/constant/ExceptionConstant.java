package com.ittovative.otpservice.constant;

public final class ExceptionConstant {
    public static final String UTILITY_CLASS_INSTANTIATION_MESSAGE = "Utility class should not be instantiated!";
    public static final String VALIDATION_ERROR_MESSAGE = "Validation error!";
    public static final String INVALID_PHONE_NUMBER_FORMAT_MESSAGE =
            "Invalid phone number format! (only digits, plus-sign and dashes are allowed).";
    public static final String INVALID_TOKEN_FORMAT_MESSAGE = "Token must be a 6-digit number!";
    public static final String INVALID_TOKEN_MESSAGE = "Invalid token!";
    public static final String TOKEN_EXPIRED_MESSAGE = "Token has expired!";


    private ExceptionConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION_MESSAGE);
    }
}
