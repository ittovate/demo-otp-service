package com.ittovative.otpservice.model;

import com.ittovative.otpservice.constant.RedisUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = RedisUtil.VERIFICATION_OTP_HASH)
public class VerificationOtp {
  @Id String phoneNumber;
  String token;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getToken() {
    return token;
  }

  public VerificationOtp(String phoneNumber, String token) {
    this.phoneNumber = phoneNumber;
    this.token = token;
  }
}
