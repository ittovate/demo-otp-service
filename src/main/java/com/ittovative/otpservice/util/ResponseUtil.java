package com.ittovative.otpservice.util;

public class ResponseUtil {

    public static<T> APIResponse<T> createUnifiedResponse(int statusCode, String message, T body){
        return new APIResponse<>(statusCode, message, body);
    }
}
