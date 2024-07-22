package com.ittovative.otpservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Otp service application.
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@SpringBootApplication
public class OtpServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(OtpServiceApplication.class, args);
    }
}
