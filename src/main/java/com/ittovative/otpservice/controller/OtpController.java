package com.ittovative.otpservice.controller;

import com.ittovative.otpservice.config.SwaggerConfig;
import com.ittovative.otpservice.constant.ApiResponseConstant;
import com.ittovative.otpservice.constant.SwaggerConstant;
import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.TokenDto;
import com.ittovative.otpservice.service.OtpService;
import com.ittovative.otpservice.util.APIResponse;
import com.ittovative.otpservice.util.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@Tag(name = SwaggerConstant.CONTROLLER_NAME, description = SwaggerConstant.CONTROLLER_DESCRIPTION)
public class OtpController implements SwaggerConfig {
    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    /**
     * Send message response entity.
     *
     * @param otpRequestDto the otp request dto
     * @return the response entity
     */
    @Override
    @PostMapping
    public APIResponse<String> sendMessage(@RequestBody @Valid OtpRequestDto otpRequestDto) {
        otpService.send(otpRequestDto);
        return ResponseUtil.createUnifiedResponse(HttpStatus.CREATED.value(), ApiResponseConstant.OTP_SENT, null);
    }

    /**
     * Verify response entity.
     *
     * @param tokenDto the verify otp request dto
     * @return the response entity
     * @throws BadRequestException the bad request exception
     */
    @Override
    @PostMapping("/verify")
    public APIResponse<String> verify(@RequestBody @Valid TokenDto tokenDto)
            throws BadRequestException {
        otpService.verifyToken(tokenDto);
        return ResponseUtil.createUnifiedResponse(HttpStatus.OK.value(), ApiResponseConstant.TOKEN_VERIFIED, null);
    }
}
