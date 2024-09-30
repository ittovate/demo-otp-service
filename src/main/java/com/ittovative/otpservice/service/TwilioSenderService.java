package com.ittovative.otpservice.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioSenderService {
    /**
     * Sends an SMS message to the specified phone number using Twilio.
     * <p>
     * This method uses the Twilio API to send an SMS message from the given {@code fromPhoneNumber}
     * to the {@code toPhoneNumber} with the provided {@code messageBody}. It creates a new message
     * using the Twilio {@link com.twilio.rest.api.v2010.account.Message#creator} method and sends it immediately.
     * </p>
     *
     * @param fromPhoneNumber The phone number from which the SMS is being sent.
     * @param toPhoneNumber   The phone number to which the SMS is being sent.
     * @param messageBody     The content of the SMS message.
     */
    public void sendSMS(String fromPhoneNumber, String toPhoneNumber, String messageBody) {
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), messageBody).create();
    }
}
