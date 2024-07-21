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

    /**
     * The Account sid.
     */
    @Value("${twilio.sid}")
    String accountSid;

    /**
     * The Auth token.
     */
    @Value("${twilio.auth-token}")
    String authToken;

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        Twilio.init(accountSid, authToken);
    }
}
