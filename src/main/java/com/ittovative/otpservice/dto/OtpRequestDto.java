package com.ittovative.otpservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.ittovative.otpservice.constant.ExceptionConstant.INVALID_PHONE_NUMBER_FORMAT;

public record OtpRequestDto(
        @NotBlank  @Pattern(regexp = "^[+0-9-]+$", message = INVALID_PHONE_NUMBER_FORMAT) String toPhoneNumber) {
}
