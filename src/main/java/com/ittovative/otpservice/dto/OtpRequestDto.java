package com.ittovative.otpservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * The type Otp request dto.
 */
public record OtpRequestDto(
        @NotBlank
                @Pattern(
                        regexp = "^[+0-9-]+$",
                        message =
                                "Invalid phone number format(Only digits, plus-sign and dashes are"
                                        + " allowed)")
                String toPhoneNumber) {}
