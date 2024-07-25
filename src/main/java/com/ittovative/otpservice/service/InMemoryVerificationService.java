package com.ittovative.otpservice.service;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;


/**
 * The type In memory verification service.
 */
@Service
public class InMemoryVerificationService implements VerificationService {
    private final Map<String, String> usersTokens;

    /**
     * Instantiates a new In memory verification service.
     *
     * @param usersTokens the users tokens
     */
    public InMemoryVerificationService(final Map<String, String> usersTokens) {
        this.usersTokens = usersTokens;
    }

    /**
     * Set user token.
     *
     * @param userPhone the user phone
     * @param token     the token
     */
    public void setUserToken(final String userPhone, final String token) {
        usersTokens.put(userPhone, token);
    }

    /**
     * Validate user token.
     *
     * @param userPhone     the user phone
     * @param receivedToken the received token
     */
    public void validateUserToken(final String userPhone, final String receivedToken)
            throws BadRequestException {
        if (!usersTokens.containsKey(userPhone)) {
            throw new NoSuchElementException("This phone did receive a token before!");
        }
        if (!usersTokens.get(userPhone).equals(receivedToken)) {
            throw new BadRequestException("Invalid token!");
        }
    }
}
