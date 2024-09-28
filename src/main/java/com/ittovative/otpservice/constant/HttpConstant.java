package com.ittovative.otpservice.constant;

import static com.ittovative.otpservice.constant.ExceptionConstant.UTILITY_CLASS_INSTANTIATION;

public final class HttpConstant {
    public static final String APPLICATION_JSON = "application/json";

    public static final String OK = "200";
    public static final String CREATED = "201";
    public static final String UPDATED = "202";
    public static final String DELETED = "203";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORBIDDEN = "403";
    public static final String NOT_FOUND = "404";
    public static final String CONFLICT = "409";
    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String METHOD_NOT_ALLOWED = "405";
    public static final String TOO_MANY_REQUESTS = "429";

    private HttpConstant() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION);
    }
}
