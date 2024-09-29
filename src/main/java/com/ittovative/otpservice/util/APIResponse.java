package com.ittovative.otpservice.util;

public record APIResponse<T>(int statusCode, String message, T body) {
}
