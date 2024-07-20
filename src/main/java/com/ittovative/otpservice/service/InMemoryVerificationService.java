package com.ittovative.otpservice.service;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.NoSuchElementException;

@Service
public class InMemoryVerificationService implements VerificationService{
    private final HashMap<String,String> usersTokens;
    public InMemoryVerificationService(HashMap<String, String> usersTokens) {
        this.usersTokens = usersTokens;
    }

    public void setUserToken(String userPhone, String token){
        usersTokens.put(userPhone,token);
    }
    public void validateUserToken(String userPhone,String receivedToken) throws BadRequestException {
        if(!usersTokens.containsKey(userPhone))
            throw new NoSuchElementException("This phone did receive a token before!");
        if(!usersTokens.get(userPhone).equals(receivedToken))
            throw new BadRequestException("Invalid token!");
    }
}