package com.ittovative.otpservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerifyOtpRequestDto(
    @NotBlank @Pattern(regexp = "^[+0-9-]+$", message = "Invalid phone number format")
        String phoneNumber,
    @NotBlank @Pattern(regexp = "^[0-9]{6}$", message = "Token must be a 6-digit number")
        String token) {}
