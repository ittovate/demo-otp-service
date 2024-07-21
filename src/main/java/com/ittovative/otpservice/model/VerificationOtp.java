package com.ittovative.otpservice.model;

import com.ittovative.otpservice.constant.RedisUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * The type Verification otp.
 */
@RedisHash(value = RedisUtil.VERIFICATION_OTP_HASH)
public class VerificationOtp {
    /**
     * The Phone number.
     */
    @Id String phoneNumber;
    /**
     * The Token.
     */
    String token;

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Instantiates a new Verification otp.
     *
     * @param phoneNumber the phone number
     * @param token       the token
     */
    public VerificationOtp(String phoneNumber, String token) {
        this.phoneNumber = phoneNumber;
        this.token = token;
    }
}
