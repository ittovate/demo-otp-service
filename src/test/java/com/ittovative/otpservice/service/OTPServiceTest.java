package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OTPRequestDTO;
import com.ittovative.otpservice.dto.TokenDTO;
import com.redis.testcontainers.RedisContainer;
import com.twilio.exception.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static com.ittovative.otpservice.constant.OtpServiceTestConstant.IMAGE_NAME;

@SpringBootTest
@Testcontainers
@SuppressWarnings("checkstyle:MethodName")
@DisplayName("OTP Service Test")
class OTPServiceTest {
    @Value("${twilio.verified-number}")
    private String verifiedPhoneNumber;

    @Value("${twilio.sender-number}")
    private String senderPhoneNumber;

    @SpyBean
    private OTPService otpService;

    @BeforeAll
    static void startTestContainer() {
        var redisContainer = new RedisContainer(DockerImageName.parse(IMAGE_NAME));
        redisContainer.start();
    }

    @Nested
    @Tag("Integration Testing")
    @DisplayName("Send OTP and verify token")
    class SendOTPAndVerifyToken {

        @Test
        @Disabled("To avoid using up free limited twilio credit")
        @DisplayName("Send OTP and verify token when both are valid")
        void Should_SendOTPAndVerifyToken_When_BothAreValid() {
            OTPRequestDTO otpRequestDto = new OTPRequestDTO(verifiedPhoneNumber);
            String actualToken = otpService.sendOTP(otpRequestDto);

            TokenDTO tokenDto = new TokenDTO(verifiedPhoneNumber, actualToken);
            Assertions.assertDoesNotThrow(() -> otpService.verifyToken(tokenDto));
        }

        @Test
        @DisplayName("Throw exception when phone number is too short")
        void Should_ThrowException_When_PhoneNumberIsTooShort() {
            OTPRequestDTO otpRequestDto = new OTPRequestDTO("+1111");

            Assertions.assertThrows(Exception.class, () -> otpService.sendOTP(otpRequestDto));
        }

        @Test
        @DisplayName("Throw exception when send and receiver have the same phone number")
        void Should_ThrowException_When_SenderAndReceiverHaveTheSamePhoneNumber() {
            OTPRequestDTO otpRequestDto = new OTPRequestDTO(senderPhoneNumber);

            Assertions.assertThrows(Exception.class, () -> otpService.sendOTP(otpRequestDto));
        }

        @Test
        @DisplayName("Throw exception when phone number is missing plus sign")
        void Should_ThrowException_When_PhoneNumberIsMissingPlusSign() {
            OTPRequestDTO otpRequestDto = new OTPRequestDTO("201007540077");

            Assertions.assertThrows(ApiException.class, () -> otpService.sendOTP(otpRequestDto));
        }
    }
}
