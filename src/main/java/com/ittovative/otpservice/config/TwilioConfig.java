package com.ittovative.otpservice.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.sid}")
    String accountSID;
    @Value("${twilio.auth-token}")
    String authToken;

    @PostConstruct
    public void initialize(){
        Twilio.init(accountSID,authToken);
    }

}
