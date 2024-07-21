package com.ittovative.otpservice.exception;

import com.ittovative.otpservice.util.ApiResponse;
import com.twilio.exception.TwilioException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * The type General exception handler.
 */
@ControllerAdvice
public class GeneralExceptionHandler {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    /**
     * Handle validation exceptions response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException exception, WebRequest webRequest) {
        //        logger.error("Exception occurred: {}, Request Details: {}",
        // exception.getMessage(),
        // webRequest.getDescription(false), exception);
        Map<String, String> errors = new HashMap<>();
        exception
                .getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });
        ApiResponse<Map<String, String>> apiResponse =
                new ApiResponse<>(errors, HttpStatus.BAD_REQUEST.value(), "Validation Error!");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ApiResponse<String>> handle(
            NoSuchElementException exception, WebRequest webRequest) {
        //        logger.error("Exception occurred: {}, Request Details: {}",
        // exception.getMessage(),
        // webRequest.getDescription(false), exception);
        ApiResponse<String> apiResponse =
                new ApiResponse<>(null, HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(TwilioException.class)
    ResponseEntity<ApiResponse<String>> handle(TwilioException exception, WebRequest webRequest) {
        //        logger.error("Exception occurred: {}, Request Details: {}",
        // exception.getMessage(),
        // webRequest.getDescription(false), exception);
        ApiResponse<String> apiResponse =
                new ApiResponse<>(null, HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ApiResponse<String>> handle(
            BadRequestException exception, WebRequest webRequest) {
        //        logger.error("Exception occurred: {}, Request Details: {}",
        // exception.getMessage(),
        // webRequest.getDescription(false), exception);
        ApiResponse<String> apiResponse =
                new ApiResponse<>(null, HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<String>> handle(Exception exception, WebRequest webRequest) {
        //        logger.error("Exception occurred: {}, Request Details: {}",
        // exception.getMessage(),
        // webRequest.getDescription(false), exception);
        ApiResponse<String> apiResponse =
                new ApiResponse<>(
                        null, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
