package com.ittovative.otpservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * The type Verify otp request dto.
 *
 * @param phoneNumber Phone number to verify with.
 * @param token       Target token to verify.
 */
public record VerifyOtpRequestDto(
        @NotBlank @Pattern(regexp = "^[+0-9-]+$", message = "Invalid phone number format")
        String phoneNumber,
        @NotBlank @Pattern(regexp = "^[0-9]{6}$", message = "Token must be a 6-digit number")
        String token) {
}
