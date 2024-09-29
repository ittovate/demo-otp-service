package com.ittovative.otpservice.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    @Value("${twilio.sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    /**
     * Initializes the Twilio API with the provided account SID and authentication token.
     * <p>
     * This method is annotated with {@code @PostConstruct}, ensuring that it is called
     * automatically after the Spring container has set up all the properties and dependencies
     * for the {@code TwilioConfig} bean. It uses the {@link com.twilio.Twilio#init(String, String)}
     * method to initialize the Twilio SDK.
     * </p>
     *
     * <p>
     * This configuration allows the application to interact with the Twilio API for sending SMS,
     * making calls, or using other Twilio services.
     * </p>
     */
    @PostConstruct
    public void initialize() {
        Twilio.init(accountSid, authToken);
    }
}
