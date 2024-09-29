package com.ittovative.otpservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.ittovative.otpservice.constant.ExceptionConstant.INVALID_PHONE_NUMBER_FORMAT;
import static com.ittovative.otpservice.constant.ExceptionConstant.INVALID_TOKEN_FORMAT;

public record TokenDTO(
        @NotBlank @Pattern(regexp = "^[+0-9-]+$", message = INVALID_PHONE_NUMBER_FORMAT) String phoneNumber,
        @NotBlank @Pattern(regexp = "^\\d{6}$", message = INVALID_TOKEN_FORMAT) String token) {
}
