package com.ittovative.otpservice.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

/**
 * The type Twilio sender service.
 */
@Service
public class TwilioSenderService {
    /**
     * Send sms.
     *
     * @param fromPhoneNumber the from phone number
     * @param toPhoneNumber   the to phone number
     * @param messageBody     the message body
     */
    public void sendSms(final String fromPhoneNumber, final String toPhoneNumber, final String messageBody) {
        Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(fromPhoneNumber),
                        messageBody)
                .create();
    }
}
