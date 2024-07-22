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
    private String accountSid;

    /**
     * The Auth token.
     */
    @Value("${twilio.auth-token}")
    private String authToken;

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        Twilio.init(accountSid, authToken);
    }

    /**
     * Gets account sid.
     *
     * @return the account sid
     */
    public String getAccountSid() {
        return accountSid;
    }

    /**
     * Gets auth token.
     *
     * @return the auth token
     */
    public String getAuthToken() {
        return authToken;
    }
}
