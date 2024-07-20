package com.ittovative.otpservice.controller;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyOtpRequestDto;
import com.ittovative.otpservice.service.SmsService;
import com.ittovative.otpservice.util.ApiResponse;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
public class OtpController {

    private final SmsService smsService;

    public OtpController(SmsService otpService) {
        this.smsService = otpService;
    }

    private Logger logger = LoggerFactory.getLogger(OtpController.class);

    @PostMapping
    public ResponseEntity<ApiResponse<String>> sendMessage(@RequestBody @Valid OtpRequestDto otpRequestDto) {
        logger.trace("");
        smsService.send(otpRequestDto);
        ApiResponse<String> apiResponse
                = new ApiResponse<>(null, HttpStatus.CREATED.value(),"Otp sent successfully!");
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<String>> verify(@RequestBody @Valid VerifyOtpRequestDto verifyOtpRequestDto) throws BadRequestException {
        smsService.verifyToken(verifyOtpRequestDto);
        ApiResponse<String> apiResponse
                = new ApiResponse<>(null,HttpStatus.OK.value(), "Token verified successfully!");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
