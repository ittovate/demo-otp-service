package com.ittovative.otpservice.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    String accountSID;
    String authToken;

    @PostConstruct
    public void initialize(){
        Twilio.init(accountSID,authToken);
    }
}
