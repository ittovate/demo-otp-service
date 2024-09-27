package com.ittovative.otpservice.util;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private final int statusCode;
    private final String message;
    private final LocalDateTime timestamp;
    private final T body;

    public ApiResponse(int statusCode, String message, T body) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public T getBody() {
        return body;
    }
}
