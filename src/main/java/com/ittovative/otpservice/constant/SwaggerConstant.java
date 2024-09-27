package com.ittovative.otpservice.constant;

public final class SwaggerConstant {
    public static final String TITLE = "OTP Service API";
    public static final String VERSION = "1.0";
    public static final String DESCRIPTION = "API to send OTP in SMS and verify it.";
    public static final String CONTACT_NAME = "ittovate";
    public static final String CONTACT_URL = "https://github.com/orgs/ittovate/";

    public static final String CONTROLLER_NAME = "OTP Controller";
    public static final String CONTROLLER_DESCRIPTION = "Send OTP and verify token";

    //================================================= Send OTP =================================================//
    public static final String SEND_OTP_SUMMARY = "Send OTP in SMS and stores it for a configured expiry date time";
    public static final String SEND_OTP_DESCRIPTION = "Generates and sends OTP to the phone number "
            + "specified in the request. This OTP is stored in order to be verified later on.";
    public static final String SEND_OTP_REQUEST_BODY_DESCRIPTION = "Phone number must be valid";
    public static final String SEND_OTP_REQUEST_BODY_EXAMPLE = """
            {
              "toPhoneNumber": "+201012060230"
            }
            """;
    public static final String OTP_SENT_RESPONSE_DESCRIPTION = "When OTP is sent successfully.";
    public static final String OTP_SENT_RESPONSE_EXAMPLE = """
            {
                "statusCode": 201,
                "message": "OTP sent successfully!",
                "timestamp": "2024-09-27T17:17:58.6897236",
                "body": null
            }
            """;
    public static final String INVALID_PHONE_NUMBER_FORMAT_RESPONSE_DESCRIPTION = "When phone number format is wrong.";
    public static final String INVALID_PHONE_NUMBER_FORMAT_RESPONSE_EXAMPLE = """
            {
                "statusCode": 400,
                "message": "Validation error!",
                "timestamp": "2024-09-27T17:46:18.3338856",
                "body": {
                    "toPhoneNumber": "Invalid phone number format! (only digits, plus-sign and dashes are allowed)."
                }
            }
            """;

    //================================================ Verify Token ================================================//
    public static final String VERIFY_TOKEN_SUMMARY = "Verify token sent by a specific number";
    public static final String VERIFY_TOKEN_DESCRIPTION =
            " Verify OTP and confirm the user's identity as well as the token expiry date.";
    public static final String VERIFY_TOKEN_REQUEST_BODY_DESCRIPTION =
            "Phone number must be valid and token must be a valid numerical 6-digit phone number";
    public static final String VERIFY_TOKEN_REQUEST_BODY_EXAMPLE = """
            {
              "phoneNumber": "+201012060230",
              "token": "552221"
            }
            """;
    public static final String TOKEN_VERIFIED_RESPONSE_DESCRIPTION = "When token is verified successfully.";
    public static final String TOKEN_VERIFIED_RESPONSE_EXAMPLE = """
            {
                "statusCode": 200,
                "message": "Token verified successfully!",
                "timestamp": "2024-09-27T18:37:32.3306115",
                "body": null
            }
            """;
    public static final String TOKEN_EXPIRED_RESPONSE_DESCRIPTION = "When token is expired.";
    public static final String TOKEN_EXPIRED_RESPONSE_EXAMPLE = """
            {
                "statusCode": 404,
                "message": "Token has expired!",
                "timestamp": "2024-09-27T18:52:32.9883657",
                "body": null
            }
            """;
    public static final String INVALID_TOKEN_RESPONSE_DESCRIPTION = "When token format is invalid.";
    public static final String INVALID_TOKEN_RESPONSE_EXAMPLE = """
            {
                "statusCode": 400,
                "message": "Validation error!",
                "timestamp": "2024-09-27T18:55:04.8928896",
                "body": {
                    "token": "Token must be a 6-digit number!"
                }
            }
            """;

    private SwaggerConstant() {
    }
}
