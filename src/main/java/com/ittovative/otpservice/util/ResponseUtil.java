package com.ittovative.otpservice.util;

public final class ResponseUtil {

    /**
     * Create unified response api response.
     *
     * @param <T>        the type parameter
     * @param statusCode the status code
     * @param message    the message
     * @param body       the body
     * @return the api response
     */
    public static <T> APIResponse<T> createUnifiedResponse(int statusCode, String message, T body) {
        return new APIResponse<>(statusCode, message, body);
    }

    private ResponseUtil() {
    }
}
