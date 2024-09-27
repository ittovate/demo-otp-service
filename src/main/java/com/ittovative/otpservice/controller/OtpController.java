package com.ittovative.otpservice.controller;

import com.ittovative.otpservice.config.SwaggerConfig;
import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyTokenRequestDto;
import com.ittovative.otpservice.service.SmsService;
import com.ittovative.otpservice.util.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ittovative.otpservice.constant.ApiResponseConstant.OTP_SENT;
import static com.ittovative.otpservice.constant.ApiResponseConstant.TOKEN_VERIFIED;
import static com.ittovative.otpservice.constant.SwaggerConstant.CONTROLLER_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.CONTROLLER_NAME;

@RestController
@RequestMapping("/api/v1/sms")
@Tag(name = CONTROLLER_NAME, description = CONTROLLER_DESCRIPTION)
public class OtpController implements SwaggerConfig {
    private final SmsService smsService;


    public OtpController(SmsService otpService) {
        this.smsService = otpService;
    }

    /**
     * Send message response entity.
     *
     * @param otpRequestDto the otp request dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<ApiResponse<String>> sendMessage(@RequestBody @Valid OtpRequestDto otpRequestDto) {
        smsService.send(otpRequestDto);

        ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), OTP_SENT, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Verify response entity.
     *
     * @param verifyTokenRequestDto the verify otp request dto
     * @return the response entity
     * @throws BadRequestException the bad request exception
     */
    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<String>> verify(@RequestBody @Valid VerifyTokenRequestDto verifyTokenRequestDto)
            throws BadRequestException {
        smsService.verifyToken(verifyTokenRequestDto);

        ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), TOKEN_VERIFIED, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
