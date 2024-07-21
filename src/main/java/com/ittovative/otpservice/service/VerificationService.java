package com.ittovative.otpservice.service;

import org.apache.coyote.BadRequestException;

/**
 * The interface Verification service.
 */
public interface VerificationService {
    /**
     * Sets user token.
     *
     * @param userPhone the user phone
     * @param token     the token
     */
              void setUserToken(String userPhone, String token);

    /**
     * Validate user token.
     *
     * @param userPhone     the user phone
     * @param receivedToken the received token
     * @throws BadRequestException the bad request exception
     */
    void validateUserToken(String userPhone, String receivedToken) throws BadRequestException;
}
