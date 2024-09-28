package com.ittovative.otpservice.constant;

public final class ExceptionConstant {
    public static final String UTILITY_CLASS_INSTANTIATION = "Utility class should not be instantiated!";
    public static final String VALIDATION_ERROR = "Validation error!";
    public static final String INVALID_PHONE_NUMBER_FORMAT =
            "Invalid phone number format! (only digits, plus-sign and dashes are allowed).";
    public static final String INVALID_TOKEN_FORMAT = "Token must be a 6-digit number!";
    public static final String INVALID_TOKEN = "Invalid token!";
    public static final String TOKEN_EXPIRED = "Token has expired!";


    private ExceptionConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION);
    }
}
