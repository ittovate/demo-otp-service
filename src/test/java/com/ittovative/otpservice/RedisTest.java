package com.ittovative.otpservice;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyOtpRequestDto;
import com.ittovative.otpservice.service.SmsService;
import com.ittovative.otpservice.service.TwilioSenderService;
import com.ittovative.otpservice.service.VerificationService;
import com.twilio.exception.ApiException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * The type Redis test.
 */
@SpringBootTest
@Testcontainers
class RedisTest {
    /**
     * The Sms service.
     */
    @SpyBean SmsService smsService;

    /**
     * The Redis.
     */
    static GenericContainer redis;

    /**
     * The Redis template.
     */
    @Spy RedisTemplate<String, String> redisTemplate;

    private @Mock TwilioSenderService twilioSenderService;
    private @Mock VerificationService verificationService;
    private @Mock RedisConnection redisConnectionMock;
    private @Mock RedisConnectionFactory redisConnectionFactoryMock;

    /**
     * The Verified number.
     */
    @Value("${twilio.verified-number}")
    String verifiedNumber;

    /**
     * Before all.
     */
    @BeforeAll
    static void beforeAll() {
        redis = new GenericContainer(DockerImageName.parse("redis")).withExposedPorts(6379);
        redis.start();
    }

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        Mockito.when(redisConnectionFactoryMock.getConnection()).thenReturn(redisConnectionMock);
    }

    /**
     * Send successful message.
     */
    @Test
    @DisplayName("Sending a successful message")
    public void sendSuccessfulMessage() {
        String actualToken = smsService.send(new OtpRequestDto(verifiedNumber));
        Assertions.assertDoesNotThrow(
                () -> smsService.verifyToken(new VerifyOtpRequestDto(verifiedNumber, actualToken)));
    }

    /**
     * Send message to a wrong phone number.
     */
    @Test
    @DisplayName("Sending an unsuccessful messsage : Too short of a Phone Number")
    public void sendMessageToAWrongPhoneNumber() {
        Assertions.assertThrows(Exception.class, () -> smsService.send(new OtpRequestDto("+1111")));
    }

    /**
     * Send message to yourself.
     */
    @Test
    @DisplayName(
            "Sending an unsuccessful messsage : Sender and Receiver have the same Phone Number")
    public void sendMessageToYourself() {
        Assertions.assertThrows(
                Exception.class, () -> smsService.send(new OtpRequestDto("+15075541680")));
    }

    /**
     * Send to an uncoded phone number.
     */
    @Test
    @DisplayName(
            "Sending an unsuccessful messsage : Sending without a code at the start of the message")
    public void sendToAnUncodedPhoneNumber() {
        Assertions.assertThrows(
                ApiException.class, () -> smsService.send(new OtpRequestDto("201007540077")));
    }
}
