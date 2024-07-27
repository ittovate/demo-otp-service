package com.ittovative.otpservice.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The type Twilio config.
 */
@Configuration
public class TwilioConfig {
    @Value("${twilio.sid}")
    private String accountSid;
    @Value("${twilio.auth-token}")
    private String authToken;

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        Twilio.init(accountSid, authToken);
    }
}
