package com.ittovative.otpservice.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioSenderService {
  public void sendSms(String fromPhoneNumber, String toPhoneNumber, String messageBody) {
    Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), messageBody)
        .create();
  }
}
