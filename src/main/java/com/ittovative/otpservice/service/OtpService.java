package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyTokenRequestDto;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OtpService implements SmsService {
    private final TwilioSenderService twilioSenderService;
    private final VerificationService verificationService;
    private final String fromPhoneNumber;
    private static final int RANDOM_UPPER_LIMIT = 900000;
    private static final int RANDOM_LOWER_LIMIT = 100000;

    public OtpService(
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
     * Send SMS message.
     *
     * @param otpRequestDto object transfer object for OTP request
     */
    @Override
    public String send(OtpRequestDto otpRequestDto) {
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
     * @param verifyTokenRequestDto the verify otp request dto
     * @throws BadRequestException the bad request exception
     */
    public void verifyToken(VerifyTokenRequestDto verifyTokenRequestDto) throws BadRequestException {
        String phoneNumber = verifyTokenRequestDto.phoneNumber();
        String token = verifyTokenRequestDto.token();
        verificationService.validateUserToken(phoneNumber, token);
    }
}
