package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OtpRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements OtpService{
    private final TwilioSenderService twilioSenderService;

    @Value("${twilio.account.phone-number}")
    private String fromPhoneNumber;

    public SmsService(TwilioSenderService twilioSenderService) {
        this.twilioSenderService = twilioSenderService;
    }

    private String generateOtp(){
        return "123456";
    }
    @Override
    public String send(OtpRequestDto otpRequestDto) {
        String otp = generateOtp();
        String smsMessage = "Here is your otp : " + otp;
        twilioSenderService.sendSms(fromPhoneNumber, otpRequestDto.toPhoneNumber(), smsMessage);
        return otp;
    }
}
