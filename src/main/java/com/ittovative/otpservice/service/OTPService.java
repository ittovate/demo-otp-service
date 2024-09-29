package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OTPRequestDTO;
import com.ittovative.otpservice.dto.TokenDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OTPService {
    private final TwilioSenderService twilioSenderService;
    private final VerificationService verificationService;
    private final String fromPhoneNumber;
    private static final int RANDOM_UPPER_LIMIT = 900000;
    private static final int RANDOM_LOWER_LIMIT = 100000;

    /**
     * Instantiates a new Otp service.
     *
     * @param fromPhoneNumber     the from phone number
     * @param twilioSenderService the twilio sender service
     * @param verificationService the verification service
     */
    public OTPService(
            @Value("${twilio.sender-number}") final String fromPhoneNumber,
            final TwilioSenderService twilioSenderService,
            final VerificationService verificationService) {
        this.twilioSenderService = twilioSenderService;
        this.verificationService = verificationService;
        this.fromPhoneNumber = fromPhoneNumber;
    }

    /**
     * Generate OTP string.
     *
     * @return the string
     */
    public String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = RANDOM_LOWER_LIMIT + random.nextInt(RANDOM_UPPER_LIMIT);
        return String.valueOf(otp);
    }


    /**
     * Send string.
     *
     * @param otpRequestDto the otp request dto
     * @return the string
     */
    public String send(OTPRequestDTO otpRequestDto) {
        String otp = generateOtp();
        String receiverPhoneNumber = otpRequestDto.toPhoneNumber();
        String smsMessage = "Here is your otp : " + otp;
        twilioSenderService.sendSms(fromPhoneNumber, receiverPhoneNumber, smsMessage);
        verificationService.setUserToken(receiverPhoneNumber, otp);
        return otp;
    }

    /**
     * Verify token.
     *
     * @param tokenDto the verify otp request dto
     * @throws BadRequestException the bad request exception
     */
    public void verifyToken(TokenDTO tokenDto) throws BadRequestException {
        String phoneNumber = tokenDto.phoneNumber();
        String token = tokenDto.token();
        verificationService.validateUserToken(phoneNumber, token);
    }
}
