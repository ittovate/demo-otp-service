package com.ittovative.otpservice.exception;

import com.ittovative.otpservice.constant.ExceptionConstant;
import com.ittovative.otpservice.util.APIResponse;
import com.ittovative.otpservice.util.ResponseUtil;
import com.twilio.exception.TwilioException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GeneralExceptionHandler {
    /**
     * Handle validation exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResponse<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        exception
                .getBindingResult()
                .getAllErrors()
                .forEach(
                        error -> {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });

        return ResponseUtil.createUnifiedResponse(
                HttpStatus.BAD_REQUEST.value(), ExceptionConstant.VALIDATION_ERROR, errors);
    }

    /**
     * Handle response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(NoSuchElementException.class)
    APIResponse<String> handle(
            NoSuchElementException exception) {
        return ResponseUtil.createUnifiedResponse(HttpStatus.NOT_FOUND.value(),
                exception.getMessage(), null);
    }

    /**
     * Handle response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(TwilioException.class)
    APIResponse<String> handle(TwilioException exception) {
        return ResponseUtil.createUnifiedResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), null);
    }

    /**
     * Handle response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(BadRequestException.class)
    APIResponse<String> handle(
            BadRequestException exception) {
        return ResponseUtil.createUnifiedResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), null);
    }

    /**
     * Handle response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    APIResponse<String> handle(Exception exception) {
        return ResponseUtil.createUnifiedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(), null);
    }
}
