package com.ittovative.otpservice.exception;

import com.ittovative.otpservice.util.APIResponse;
import com.twilio.exception.TwilioException;
import org.apache.coyote.BadRequestException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.ittovative.otpservice.constant.ExceptionConstant.VALIDATION_ERROR_MESSAGE;
import static com.ittovative.otpservice.util.APIResponseUtil.createUnifiedResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GeneralExceptionHandler {
    /**
     * Handles {@link MethodArgumentNotValidException} exceptions and returns a formatted error response.
     * <p>
     * This method is invoked when a request's validation fails, typically due to invalid data provided
     * in a {@code @RequestBody}. It extracts the validation errors from the exception, maps each field
     * to its corresponding error message, and returns them in a structured format.
     * </p>
     * <p>
     * The response includes:
     * <ul>
     *     <li>An HTTP status code of {@code 400 Bad Request}</li>
     *     <li>A generic validation error message</li>
     *     <li>A map of specific field validation errors,
     *     where the field name is the key and the error message is the value</li>
     * </ul>
     * </p>
     *
     * @param exception The exception that is thrown when method arguments fail validation,
     *                  containing the details of all validation errors.
     * @return An {@code APIResponse} containing the HTTP status code, a validation error message,
     * and a map of field-specific validation errors.
     * @see MethodArgumentNotValidException
     * @see org.springframework.validation.FieldError
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResponse<Map<String, String>> handle(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return createUnifiedResponse(BAD_REQUEST.value(), VALIDATION_ERROR_MESSAGE, errors);
    }

    /**
     * Handles {@link NoSuchElementException} and returns a custom error response.
     * <p>
     * This method is invoked when an element that is expected to be found in the system
     * (such as a database record) does not exist, causing a {@code NoSuchElementException} to be thrown.
     * </p>
     * <p>
     * The response includes:
     * <ul>
     *     <li>An HTTP status code of {@code 404 Not Found}</li>
     *     <li>The exception's message as the error message, describing the missing element</li>
     *     <li>A null data payload, since no valid data could be retrieved</li>
     * </ul>
     * </p>
     *
     * @param exception The exception thrown when an element is not found.
     * @return An {@code APIResponse} containing the HTTP status code, the exception's message, and a null data payload.
     * @see NoSuchElementException
     */
    @ExceptionHandler(NoSuchElementException.class)
    public APIResponse<String> handle(NoSuchElementException exception) {
        return createUnifiedResponse(NOT_FOUND.value(), exception.getMessage(), null);
    }

    /**
     * Handles {@link com.twilio.exception.TwilioException} and returns a custom error response.
     * <p>
     * This method is invoked when an error occurs while interacting with the Twilio API,
     * causing a {@code TwilioException} to be thrown.
     * The error could be due to various reasons such as invalid credentials, network issues, or misconfiguration.
     * </p>
     * <p>
     * The response includes:
     * <ul>
     *     <li>An HTTP status code of {@code 400 Bad Request}</li>
     *     <li>The exception's message as the error message, providing details about the Twilio-related issue</li>
     *     <li>A null data payload, since no valid data could be retrieved from the request</li>
     * </ul>
     * </p>
     *
     * @param exception The exception thrown during interaction with Twilio services.
     * @return An {@code APIResponse} containing the HTTP status code, the exception's message, and a null data payload.
     * @see com.twilio.exception.TwilioException
     */
    @ExceptionHandler(TwilioException.class)
    public APIResponse<String> handle(TwilioException exception) {
        return createUnifiedResponse(BAD_REQUEST.value(), exception.getMessage(), null);
    }

    /**
     * Handles {@link BadRequestException} and returns a custom error response.
     * <p>
     * This method is invoked when a {@code BadRequestException} is thrown, typically due to invalid
     * input or malformed requests sent by the client. The exception message contains details about the
     * specific issue with the request.
     * </p>
     * <p>
     * The response includes:
     * <ul>
     *     <li>An HTTP status code of {@code 400 Bad Request}</li>
     *     <li>The exception's message as the error message, explaining why the request was invalid</li>
     *     <li>A null data payload, as no valid data can be returned due to the error</li>
     * </ul>
     * </p>
     *
     * @param exception The exception thrown when a bad request is encountered.
     * @return An {@code APIResponse} containing the HTTP status code, the exception's message, and a null data payload.
     * @see BadRequestException
     */
    @ExceptionHandler(BadRequestException.class)
    public APIResponse<String> handle(BadRequestException exception) {
        return createUnifiedResponse(BAD_REQUEST.value(), exception.getMessage(), null);
    }

    /**
     * Handles generic {@link Exception} and returns a custom error response for unexpected errors.
     * <p>
     * This method is invoked when an unhandled or unexpected exception occurs in the application.
     * It acts as a fallback for any exceptions that are not specifically handled by other exception handlers.
     * </p>
     * <p>
     * The response includes:
     * <ul>
     *     <li>An HTTP status code of {@code 500 Internal Server Error}</li>
     *     <li>The exception's message as the error message, which provides information about the unexpected error</li>
     *     <li>A null data payload, as no valid data can be retrieved due to the error</li>
     * </ul>
     * </p>
     *
     * @param exception The generic exception that occurred during the application's execution.
     * @return An {@code APIResponse} containing the HTTP status code, the exception's message, and a null data payload.
     * @see Exception
     */
    @ExceptionHandler(Exception.class)
    public APIResponse<String> handle(Exception exception) {
        return createUnifiedResponse(INTERNAL_SERVER_ERROR.value(), exception.getMessage(), null);
    }
}
