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
    private final TwilioSenderService twilioSenderService;
    private final VerificationService verificationService;

    private final String fromPhoneNumber;

    /**
     * Instantiates a new Sms service.
     *
     * @param fromPhoneNumber     the from phone number
     * @param twilioSenderService the twilio sender service
     * @param verificationService the verification service
     */
    public SmsService(
            @Value("${twilio.sender-number}") String fromPhoneNumber,
            TwilioSenderService twilioSenderService,
            VerificationService verificationService) {
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
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

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
     * @param verifyOtpRequestDto the verify otp request dto
     * @throws BadRequestException the bad request exception
     */
    public void verifyToken(VerifyOtpRequestDto verifyOtpRequestDto) throws BadRequestException {
        String phoneNumber = verifyOtpRequestDto.phoneNumber();
        String token = verifyOtpRequestDto.token();
        verificationService.validateUserToken(phoneNumber, token);
    }
}
