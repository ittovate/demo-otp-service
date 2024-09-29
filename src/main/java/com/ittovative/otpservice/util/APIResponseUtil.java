package com.ittovative.otpservice.util;

import static com.ittovative.otpservice.constant.ExceptionConstant.UTILITY_CLASS_INSTANTIATION_MESSAGE;

public final class APIResponseUtil {

    /**
     * Creates a unified API response with the given status code, message, and response body.
     * <p>
     * This utility method constructs a standardized {@link APIResponse} object, encapsulating the status code,
     * message, and body into a single response format. This method ensures consistent API responses throughout
     * the application.
     * </p>
     *
     * @param <T>        The type of the body in the response.
     * @param statusCode The HTTP status code to be included in the response.
     * @param message    A descriptive message to accompany the status code.
     * @param body       The body of the response, which can contain data or be {@code null} if no data is returned.
     * @return An {@link APIResponse} object containing the provided status code, message, and body.
     */
    public static <T> APIResponse<T> createUnifiedResponse(int statusCode, String message, T body) {
        return new APIResponse<>(statusCode, message, body);
    }

    private APIResponseUtil() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION_MESSAGE);
    }
}
