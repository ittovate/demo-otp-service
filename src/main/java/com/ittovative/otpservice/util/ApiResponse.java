package com.ittovative.otpservice.util;

/**
 * The type Api response.
 *
 * @param <T> the type parameter
 */
public class ApiResponse<T> {
    /**
     * The Body.
     */
    private final T body;

    /**
     * The Status code.
     */
    private final int statusCode;

    /**
     * The Message.
     */
    private final String message;

    /**
     * Gets body.
     *
     * @return the body
     */
    public T getBody() {
        return body;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Instantiates a new Api response.
     *
     * @param body       the body
     * @param statusCode the status code
     * @param message    the message
     */
    public ApiResponse(final T body, final int statusCode, final String message) {
        this.body = body;
        this.statusCode = statusCode;
        this.message = message;
    }
}
