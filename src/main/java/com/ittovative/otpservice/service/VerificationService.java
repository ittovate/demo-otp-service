package com.ittovative.otpservice.service;

import org.apache.coyote.BadRequestException;

public interface VerificationService {
    void setUserToken(String userPhone,String token);
    void validateUserToken(String userPhone,String receivedToken) throws BadRequestException;
}