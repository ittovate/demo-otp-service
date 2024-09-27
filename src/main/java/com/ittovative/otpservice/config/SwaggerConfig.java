package com.ittovative.otpservice.config;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyTokenRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import static com.ittovative.otpservice.constant.HttpConstant.APPLICATION_JSON;
import static com.ittovative.otpservice.constant.HttpConstant.BAD_REQUEST;
import static com.ittovative.otpservice.constant.HttpConstant.CREATED;
import static com.ittovative.otpservice.constant.HttpConstant.NOT_FOUND;
import static com.ittovative.otpservice.constant.HttpConstant.OK;
import static com.ittovative.otpservice.constant.SwaggerConstant.INVALID_PHONE_NUMBER_FORMAT_RESPONSE_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.INVALID_PHONE_NUMBER_FORMAT_RESPONSE_EXAMPLE;
import static com.ittovative.otpservice.constant.SwaggerConstant.INVALID_TOKEN_RESPONSE_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.INVALID_TOKEN_RESPONSE_EXAMPLE;
import static com.ittovative.otpservice.constant.SwaggerConstant.OTP_SENT_RESPONSE_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.OTP_SENT_RESPONSE_EXAMPLE;
import static com.ittovative.otpservice.constant.SwaggerConstant.SEND_OTP_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.SEND_OTP_REQUEST_BODY_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.SEND_OTP_REQUEST_BODY_EXAMPLE;
import static com.ittovative.otpservice.constant.SwaggerConstant.SEND_OTP_SUMMARY;
import static com.ittovative.otpservice.constant.SwaggerConstant.TOKEN_EXPIRED_RESPONSE_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.TOKEN_EXPIRED_RESPONSE_EXAMPLE;
import static com.ittovative.otpservice.constant.SwaggerConstant.TOKEN_VERIFIED_RESPONSE_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.TOKEN_VERIFIED_RESPONSE_EXAMPLE;
import static com.ittovative.otpservice.constant.SwaggerConstant.VERIFY_TOKEN_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.VERIFY_TOKEN_REQUEST_BODY_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.VERIFY_TOKEN_REQUEST_BODY_EXAMPLE;
import static com.ittovative.otpservice.constant.SwaggerConstant.VERIFY_TOKEN_SUMMARY;

@SuppressWarnings("checkstyle:MissingJavadocMethod")
public interface SwaggerConfig {
    @Operation(summary = SEND_OTP_SUMMARY, description = SEND_OTP_DESCRIPTION,
            requestBody = @RequestBody(
                    required = true,
                    description = SEND_OTP_REQUEST_BODY_DESCRIPTION,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = OtpRequestDto.class),
                            examples = @ExampleObject(value = SEND_OTP_REQUEST_BODY_EXAMPLE)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = CREATED,
                            description = OTP_SENT_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = com.ittovative.otpservice.util.ApiResponse.class),
                                    examples = @ExampleObject(value = OTP_SENT_RESPONSE_EXAMPLE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = BAD_REQUEST,
                            description = INVALID_PHONE_NUMBER_FORMAT_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = com.ittovative.otpservice.util.ApiResponse.class),
                                    examples = @ExampleObject(value = INVALID_PHONE_NUMBER_FORMAT_RESPONSE_EXAMPLE)
                            )
                    )
            }
    )
    ResponseEntity<com.ittovative.otpservice.util.ApiResponse<String>> sendMessage(OtpRequestDto otpRequestDto);

    @Operation(summary = VERIFY_TOKEN_SUMMARY, description = VERIFY_TOKEN_DESCRIPTION,
            requestBody = @RequestBody(
                    required = true,
                    description = VERIFY_TOKEN_REQUEST_BODY_DESCRIPTION,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = OtpRequestDto.class),
                            examples = @ExampleObject(value = VERIFY_TOKEN_REQUEST_BODY_EXAMPLE)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = OK,
                            description = TOKEN_VERIFIED_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = com.ittovative.otpservice.util.ApiResponse.class),
                                    examples = @ExampleObject(value = TOKEN_VERIFIED_RESPONSE_EXAMPLE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = NOT_FOUND,
                            description = TOKEN_EXPIRED_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = com.ittovative.otpservice.util.ApiResponse.class),
                                    examples = @ExampleObject(value = TOKEN_EXPIRED_RESPONSE_EXAMPLE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = BAD_REQUEST,
                            description = INVALID_TOKEN_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = com.ittovative.otpservice.util.ApiResponse.class),
                                    examples = @ExampleObject(value = INVALID_TOKEN_RESPONSE_EXAMPLE)
                            )
                    ),
            }
    )
    ResponseEntity<com.ittovative.otpservice.util.ApiResponse<String>> verify(
            VerifyTokenRequestDto verifyTokenRequestDto) throws BadRequestException;
}
