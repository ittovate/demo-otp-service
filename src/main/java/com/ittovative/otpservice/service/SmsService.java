package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OtpRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SmsService implements OtpService{
    private final TwilioSenderService twilioSenderService;

    @Value("${twilio.sender-number}")
    private String fromPhoneNumber;
    public SmsService(TwilioSenderService twilioSenderService) {
        this.twilioSenderService = twilioSenderService;
    }

    public String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public String send(OtpRequestDto otpRequestDto) {
        String otp = generateOtp();
        String smsMessage = "Here is your otp : " + otp;
        twilioSenderService.sendSms(fromPhoneNumber, otpRequestDto.toPhoneNumber(), smsMessage);
        return otp;
    }
}
