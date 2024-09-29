package com.ittovative.otpservice.config;

import com.ittovative.otpservice.dto.OTPRequestDTO;
import com.ittovative.otpservice.dto.TokenDTO;
import com.ittovative.otpservice.util.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.coyote.BadRequestException;

import static com.ittovative.otpservice.constant.HTTPConstant.APPLICATION_JSON;
import static com.ittovative.otpservice.constant.HTTPConstant.BAD_REQUEST;
import static com.ittovative.otpservice.constant.HTTPConstant.CREATED;
import static com.ittovative.otpservice.constant.HTTPConstant.NOT_FOUND;
import static com.ittovative.otpservice.constant.HTTPConstant.OK;
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
                            schema = @Schema(implementation = OTPRequestDTO.class),
                            examples = @ExampleObject(value = SEND_OTP_REQUEST_BODY_EXAMPLE)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = CREATED,
                            description = OTP_SENT_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = APIResponse.class),
                                    examples = @ExampleObject(value = OTP_SENT_RESPONSE_EXAMPLE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = BAD_REQUEST,
                            description = INVALID_PHONE_NUMBER_FORMAT_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = APIResponse.class),
                                    examples = @ExampleObject(value = INVALID_PHONE_NUMBER_FORMAT_RESPONSE_EXAMPLE)
                            )
                    )
            }
    )
    APIResponse<String> sendMessage(OTPRequestDTO otpRequestDto);

    @Operation(summary = VERIFY_TOKEN_SUMMARY, description = VERIFY_TOKEN_DESCRIPTION,
            requestBody = @RequestBody(
                    required = true,
                    description = VERIFY_TOKEN_REQUEST_BODY_DESCRIPTION,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = OTPRequestDTO.class),
                            examples = @ExampleObject(value = VERIFY_TOKEN_REQUEST_BODY_EXAMPLE)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = OK,
                            description = TOKEN_VERIFIED_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = APIResponse.class),
                                    examples = @ExampleObject(value = TOKEN_VERIFIED_RESPONSE_EXAMPLE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = NOT_FOUND,
                            description = TOKEN_EXPIRED_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = APIResponse.class),
                                    examples = @ExampleObject(value = TOKEN_EXPIRED_RESPONSE_EXAMPLE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = BAD_REQUEST,
                            description = INVALID_TOKEN_RESPONSE_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = APIResponse.class),
                                    examples = @ExampleObject(value = INVALID_TOKEN_RESPONSE_EXAMPLE)
                            )
                    ),
            }
    )
    APIResponse<String> verify(TokenDTO tokenDto) throws BadRequestException;
}
