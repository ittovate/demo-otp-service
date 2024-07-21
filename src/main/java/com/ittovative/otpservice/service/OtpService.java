package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OtpRequestDto;

public interface OtpService {
  String send(OtpRequestDto otpRequestDto);
}
