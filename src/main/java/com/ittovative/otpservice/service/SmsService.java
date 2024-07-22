package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyOtpRequestDto;

import java.security.SecureRandom;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Sms service.
 */
@Service
public class SmsService implements OtpService {
    /**
     * The TwilioSenderService instance.
     */
    private final TwilioSenderService twilioSenderService;
    /**
     * The VerificationService instance.
     */
    private final VerificationService verificationService;
    /**
     * The sender phone number.
     */
    private final String fromPhoneNumber;
    /**
     * Upper limit for generating OTP.
     */
    private static final int RANDOM_UPPER_LIMIT = 900000;
    /**
     * Lower limit for generating OTP.
     */
    private static final int RANDOM_LOWER_LIMIT = 100000;

    /**
     * Instantiates a new Sms service.
     *
     * @param fromPhoneNumber     the from phone number
     * @param twilioSenderService the twilio sender service
     * @param verificationService the verification service
     */
    public SmsService(
            @Value("${twilio.sender-number}") final String fromPhoneNumber,
            final TwilioSenderService twilioSenderService,
            final VerificationService verificationService) {
        this.twilioSenderService = twilioSenderService;
        this.verificationService = verificationService;
        this.fromPhoneNumber = fromPhoneNumber;
    }

    /**
     * Generate otp string.
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
    public String send(final OtpRequestDto otpRequestDto) {
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
     * @param verifyOtpRequestDto the verify otp request dto
     * @throws BadRequestException the bad request exception
     */
    public void verifyToken(final VerifyOtpRequestDto verifyOtpRequestDto) throws BadRequestException {
        String phoneNumber = verifyOtpRequestDto.phoneNumber();
        String token = verifyOtpRequestDto.token();
        verificationService.validateUserToken(phoneNumber, token);
    }
}
