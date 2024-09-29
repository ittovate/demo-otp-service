package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OTPRequestDTO;
import com.ittovative.otpservice.dto.TokenDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static com.ittovative.otpservice.constant.ServiceConstant.OTP_SMS_MESSAGE;
import static com.ittovative.otpservice.constant.ServiceConstant.RANDOM_LOWER_LIMIT;
import static com.ittovative.otpservice.constant.ServiceConstant.RANDOM_UPPER_LIMIT;
import static java.lang.String.format;

@Service
public class OTPService {
    private final TwilioSenderService twilioSenderService;
    private final VerificationService verificationService;
    private final String fromPhoneNumber;

    public OTPService(
            @Value("${twilio.sender-number}") String fromPhoneNumber,
            TwilioSenderService twilioSenderService,
            VerificationService verificationService) {
        this.twilioSenderService = twilioSenderService;
        this.verificationService = verificationService;
        this.fromPhoneNumber = fromPhoneNumber;
    }

    /**
     * Generates a One-Time Password (OTP) as a random 6-digit number.
     * <p>
     * This method uses a {@link SecureRandom} instance to generate a secure random number
     * within the specified range for OTPs.
     * </p>
     *
     * @return A string representation of the generated OTP.
     */
    public String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = RANDOM_LOWER_LIMIT + random.nextInt(RANDOM_UPPER_LIMIT);
        return String.valueOf(otp);
    }

    /**
     * Sends a One-Time Password (OTP) via SMS to the user's phone number and stores the OTP for verification.
     * <p>
     * This method generates a new OTP by calling {@link #generateOTP()}, formats it into an SMS message, and sends it
     * to the user's phone number obtained from the {@code otpRequestDto}.
     * After sending the OTP, the method stores the OTP
     * in the verification service to be used later for validation.
     * </p>
     * <p>
     * The SMS is sent using {@code twilioSenderService}, and the OTP is stored for the receiver's phone number
     * using {@code verificationService}.
     * </p>
     *
     * @param otpRequestDto A Data Transfer Object (DTO) containing the user's phone number and other relevant details.
     * @return The generated OTP as a string.
     */
    public String sendOTP(OTPRequestDTO otpRequestDto) {
        String otp = generateOTP();
        String receiverPhoneNumber = otpRequestDto.toPhoneNumber();

        twilioSenderService.sendSMS(fromPhoneNumber, receiverPhoneNumber, format(OTP_SMS_MESSAGE, otp));
        verificationService.setUserToken(receiverPhoneNumber, otp);
        return otp;
    }

    /**
     * Verifies the One-Time Password (OTP) token provided by the user.
     * <p>
     * This method checks the provided OTP token against the stored token for the specified phone number.
     * The phone number and token are extracted from the {@code tokenDto}. The method then calls the
     * {@code verificationService} to validate the OTP.
     * If the token is invalid or expired, a {@link BadRequestException} is thrown.
     * </p>
     *
     * @param tokenDto A Data Transfer Object (DTO) containing the user's phone number
     *                 and the OTP token for verification.
     * @throws BadRequestException If the provided token is invalid or has expired.
     */
    public void verifyToken(TokenDTO tokenDto) throws BadRequestException {
        String phoneNumber = tokenDto.phoneNumber();
        String token = tokenDto.token();
        verificationService.validateUserToken(phoneNumber, token);
    }
}
