package com.ittovative.otpservice;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.service.SmsService;
import com.ittovative.otpservice.service.TwilioSenderService;
import com.ittovative.otpservice.service.VerificationService;
import com.twilio.exception.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @Value("${twilio.verified-number}")
    private String verifiedNumber;
    @Mock
    private TwilioSenderService twilioSenderService;
    @Mock
    private VerificationService verificationService;
    @SpyBean
    private SmsService smsService;
    private static GenericContainer redis;
    private static final int REDIS_PORT = 6379;
    @Spy
    private RedisTemplate<String, String> redisTemplate;
    @Mock
    private RedisConnection redisConnectionMock;
    @Mock
    private RedisConnectionFactory redisConnectionFactoryMock;

    public SmsService getSmsService() {
        return smsService;
    }

    public static GenericContainer getRedis() {
        return redis;
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public TwilioSenderService getTwilioSenderService() {
        return twilioSenderService;
    }

    public VerificationService getVerificationService() {
        return verificationService;
    }

    public RedisConnection getRedisConnectionMock() {
        return redisConnectionMock;
    }

    public RedisConnectionFactory getRedisConnectionFactoryMock() {
        return redisConnectionFactoryMock;
    }

    public String getVerifiedNumber() {
        return verifiedNumber;
    }

    /**
     * Before all.
     */
    @BeforeAll
    static void beforeAll() {
        redis = new GenericContainer(DockerImageName.parse("redis")).withExposedPorts(REDIS_PORT);
        redis.start();
    }

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        Mockito.when(redisConnectionFactoryMock.getConnection()).thenReturn(redisConnectionMock);
    }

//    @Test
//    @DisplayName("Sending a successful message")
//    void sendSuccessfulMessage() {
//        String actualToken = smsService.send(new OtpRequestDto(verifiedNumber));
//        Assertions.assertDoesNotThrow(
//                () -> smsService.verifyToken(new VerifyOtpRequestDto(verifiedNumber, actualToken)));
//    }

    /**
     * Send message to a wrong phone number.
     */
    @Test
    @DisplayName("Sending an unsuccessful messsage : Too short of a Phone Number")
    void sendMessageToAWrongPhoneNumber() {
        Assertions.assertThrows(Exception.class, () -> smsService.send(new OtpRequestDto("+1111")));
    }

    /**
     * Send message to yourself.
     */
    @Test
    @DisplayName(
            "Sending an unsuccessful message : Sender and Receiver have the same Phone Number")
    void sendMessageToYourself() {
        Assertions.assertThrows(
                Exception.class, () -> smsService.send(new OtpRequestDto("+15075541680")));
    }

    /**
     * Send to an encoded phone number.
     */
    @Test
    @DisplayName(
            "Sending an unsuccessful message : Sending without a code at the start of the message")
    void sendToAnUncodedPhoneNumber() {
        Assertions.assertThrows(
                ApiException.class, () -> smsService.send(new OtpRequestDto("201007540077")));
    }
}
