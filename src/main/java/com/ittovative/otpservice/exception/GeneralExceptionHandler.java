package com.ittovative.otpservice.exception;

import com.ittovative.otpservice.util.ApiResponse;
import com.twilio.exception.TwilioException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GeneralExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ApiResponse<String>> handle(NoSuchElementException noSuchElementException){
        logger.error(noSuchElementException.getMessage());
        ApiResponse<String> apiResponse
                = new ApiResponse<>(null,HttpStatus.NOT_FOUND.value(), noSuchElementException.getMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TwilioException.class)
    ResponseEntity<ApiResponse<String>> handle(TwilioException exception){
        logger.error(exception.getMessage());
        ApiResponse<String> apiResponse
                = new ApiResponse<>(null,HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ApiResponse<String>> handle(BadRequestException badRequestException){
        logger.error(badRequestException.getMessage());
        ApiResponse<String> apiResponse
                = new ApiResponse<>(null,HttpStatus.BAD_REQUEST.value(), badRequestException.getMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<String>> handle(Exception exception){
        logger.error(exception.getMessage());
        ApiResponse<String> apiResponse
                = new ApiResponse<>(null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
