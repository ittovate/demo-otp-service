package com.ittovative.otpservice.controller;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyOtpRequestDto;
import com.ittovative.otpservice.service.SmsService;
import com.ittovative.otpservice.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Otp controller.
 */
@RestController
@RequestMapping("/api/v1/sms")
public class OtpController {
    private final SmsService smsService;

    /**
     * Instantiates a new Otp controller.
     *
     * @param otpService the otp service
     */
    public OtpController(final SmsService otpService) {
        this.smsService = otpService;
    }

    /**
     * Send message response entity.
     *
     * @param otpRequestDto the otp request dto
     * @return the response entity
     */
    @Operation(
            summary = "Sends OTP and stores it for a configured expiry date time",
            description =
                    "Generates and sends a One-Time Password (OTP) to the phone numberspecified in"
                            + " the request. This OTP is stored in order to be verified later on.",
            requestBody =
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Must be a valid phone number"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "OTP sent successfully",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples =
                                    @ExampleObject(
                                            value =
                                                    "{\"body\": null, \"statusCode\":"
                                                            + " 201, \"message\": \"Otp"
                                                            + " sent successfully!\"}"))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid parameters",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples =
                                    @ExampleObject(
                                            value =
                                                    "{\"body\": null, \"statusCode\":"
                                                            + " 400, \"message\":"
                                                            + " \"Validation Error\"}"))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples =
                                    @ExampleObject(
                                            value =
                                                    "{\"body\": null, \"statusCode\":"
                                                            + " 500, \"message\":"
                                                            + " \"Internal server"
                                                            + " error\"}")))
            })
    @PostMapping
    public ResponseEntity<ApiResponse<String>> sendMessage(
            @RequestBody @Valid final OtpRequestDto otpRequestDto) {
        smsService.send(otpRequestDto);
        ApiResponse<String> apiResponse =
                new ApiResponse<>(null, HttpStatus.CREATED.value(), "Otp sent successfully!");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Verify response entity.
     *
     * @param verifyOtpRequestDto the verify otp request dto
     * @return the response entity
     * @throws BadRequestException the bad request exception
     */
    @Operation(
            summary = "Verifies the OTP sent by a specific number",
            description =
                    " This endpoint checks the validity of the OTP and confirms the user's"
                            + " identity as well as the token expiry date.",
            requestBody =
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description =
                            "Phone number must be a valid phone number . Token must be a"
                                    + " valid numerical 6-digit phone number"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Token verified successfully",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples =
                                    @ExampleObject(
                                            value =
                                                    "{\"body\": null, \"status\": 200,"
                                                            + " \"message\": \"Token"
                                                            + " verified"
                                                            + " successfully!\"}"))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid parameters or expired OTP",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples =
                                    @ExampleObject(
                                            value =
                                                    "{\"body\": null, \"statusCode\":"
                                                            + " 400, \"message\":"
                                                            + " \"Validation Error\"}"))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content =
                            @Content(
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples =
                                    @ExampleObject(
                                            value =
                                                    "{\"body\": null, \"statusCode\":"
                                                            + " 500, \"message\":"
                                                            + " \"Internal server"
                                                            + " error\"}")))
            })
    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<String>> verify(
            @RequestBody @Valid final VerifyOtpRequestDto verifyOtpRequestDto)
            throws BadRequestException {
        smsService.verifyToken(verifyOtpRequestDto);
        ApiResponse<String> apiResponse =
                new ApiResponse<>(null, HttpStatus.OK.value(), "Token verified successfully!");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
