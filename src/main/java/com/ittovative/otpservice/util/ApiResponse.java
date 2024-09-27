package com.ittovative.otpservice.util;

public class ApiResponse<T> {
    private final T body;
    private final int statusCode;
    private final String message;

    public T getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }


    public ApiResponse(T body, int statusCode, String message) {
        this.body = body;
        this.statusCode = statusCode;
        this.message = message;
    }
}
