package com.ittovative.otpservice.controller;

import com.ittovative.otpservice.config.SwaggerConfig;
import com.ittovative.otpservice.dto.OTPRequestDTO;
import com.ittovative.otpservice.dto.TokenDTO;
import com.ittovative.otpservice.service.OTPService;
import com.ittovative.otpservice.util.APIResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ittovative.otpservice.constant.APIResponseConstant.OTP_SENT_MESSAGE;
import static com.ittovative.otpservice.constant.APIResponseConstant.TOKEN_VERIFIED_MESSAGE;
import static com.ittovative.otpservice.constant.SwaggerConstant.CONTROLLER_DESCRIPTION;
import static com.ittovative.otpservice.constant.SwaggerConstant.CONTROLLER_NAME;
import static com.ittovative.otpservice.util.APIResponseUtil.createUnifiedResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/sms")
@Tag(name = CONTROLLER_NAME, description = CONTROLLER_DESCRIPTION)
public class OTPController implements SwaggerConfig {
    private final OTPService otpService;

    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    /**
     * Sends an SMS message containing a One-Time Password (OTP) to the user.
     * <p>
     * This method receives an OTP request via the {@code otpRequestDto} parameter, which contains
     * the user's phone number. The OTP is generated and sent to the specified phone number
     * using the underlying {@code OTPService}.
     * </p>
     * <p>
     * Upon successful completion, the method returns an {@code APIResponse} object with an HTTP status
     * code of {@code 201 Created} and a message indicating that the OTP was successfully sent.
     * </p>
     *
     * @param otpRequestDto A Data Transfer Object (DTO) containing the required information for sending an OTP,
     *                      such as the phone number.
     * @return An {@code APIResponse} containing a message confirming that the OTP has been sent,
     * along with the appropriate HTTP status code.
     * @see OTPService#sendOTP(OTPRequestDTO)
     */
    @Override
    @PostMapping
    public APIResponse<String> sendMessage(@RequestBody @Valid OTPRequestDTO otpRequestDto) {
        otpService.sendOTP(otpRequestDto);
        return createUnifiedResponse(CREATED.value(), OTP_SENT_MESSAGE, null);
    }

    /**
     * Verifies the OTP token sent by the user.
     * <p>
     * This method receives a {@code tokenDto} object containing the user's token, which is then
     * verified using the {@code OTPService}. If the token is valid, the method returns a success message.
     * In case of an invalid or expired token, the underlying service throws an appropriate exception.
     * </p>
     * <p>
     * The method responds with an HTTP status of {@code 200 OK} upon successful verification, along with
     * a confirmation message. If the verification fails, a {@code BadRequestException} is thrown, indicating
     * an error with the provided token.
     * </p>
     *
     * @param tokenDto A Data Transfer Object (DTO) containing the token sent by the user for verification.
     * @return An {@code APIResponse} containing a message confirming that the token has been successfully verified.
     *
     * @throws BadRequestException if the token is invalid or has expired.
     *
     * @see OTPService#verifyToken(TokenDTO)
     */
    @Override
    @PostMapping("/verify")
    public APIResponse<String> verify(@RequestBody @Valid TokenDTO tokenDto) throws BadRequestException {
        otpService.verifyToken(tokenDto);
        return createUnifiedResponse(OK.value(), TOKEN_VERIFIED_MESSAGE, null);
    }
}
