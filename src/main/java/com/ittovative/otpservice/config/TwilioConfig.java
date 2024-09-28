package com.ittovative.otpservice.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    @Value("${twilio.sid}")
    private static String accountSid;
    @Value("${twilio.auth-token}")
    private static String authToken;
    @Value("${twilio.verified-number}")
    private static String verifiedNumber;

    public static String getVerifiedNumber() {
        return verifiedNumber;
    }

    public static String getAccountSid() {
        return accountSid;
    }

    public static String getAuthToken() {
        return authToken;
    }

    /**
     * Initialize Twilio with account SID and auth token.
     */
    @PostConstruct
    public void initialize() {
        Twilio.init(accountSid, authToken);
    }
}
