package com.ittovative.otpservice;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyOtpRequestDto;
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
    /**
     * The Sms service.
     */
    @SpyBean
    private SmsService smsService;

    /**
     * The Redis.
     */
    private static GenericContainer redis;

    /**
     * The Redis template.
     */
    @Spy
    private RedisTemplate<String, String> redisTemplate;

    /**
     * The Verified number.
     */
    @Value("${twilio.verified-number}")
    private String verifiedNumber;

    /**
     * The TwilioSenderService instance.
     */
    private @Mock TwilioSenderService twilioSenderService;
    /**
     * The VerificationService instance.
     */
    private @Mock VerificationService verificationService;
    /**
     * The RedisConnection instance.
     */
    private @Mock RedisConnection redisConnectionMock;
    /**
     * The RedisConnectionFactory instance.
     */
    private @Mock RedisConnectionFactory redisConnectionFactoryMock;

    /**
     * The REDIS_PORT constant.
     */
    private static final int REDIS_PORT = 6379;

    /**
     * Gets sms service.
     *
     * @return the sms service
     */
    public SmsService getSmsService() {
        return smsService;
    }

    /**
     * Gets redis.
     *
     * @return the redis
     */
    public static GenericContainer getRedis() {
        return redis;
    }

    /**
     * Gets redis template.
     *
     * @return the redis template
     */
    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * Gets twilio sender service.
     *
     * @return the twilio sender service
     */
    public TwilioSenderService getTwilioSenderService() {
        return twilioSenderService;
    }

    /**
     * Gets verification service.
     *
     * @return the verification service
     */
    public VerificationService getVerificationService() {
        return verificationService;
    }

    /**
     * Gets redis connection mock.
     *
     * @return the redis connection mock
     */
    public RedisConnection getRedisConnectionMock() {
        return redisConnectionMock;
    }

    /**
     * Gets redis connection factory mock.
     *
     * @return the redis connection factory mock
     */
    public RedisConnectionFactory getRedisConnectionFactoryMock() {
        return redisConnectionFactoryMock;
    }

    /**
     * Gets verified number.
     *
     * @return the verified number
     */
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

    /**
     * Send successful message.
     */
    @Test
    @DisplayName("Sending a successful message")
    void sendSuccessfulMessage() {
        String actualToken = smsService.send(new OtpRequestDto(verifiedNumber));
        Assertions.assertDoesNotThrow(
                () -> smsService.verifyToken(new VerifyOtpRequestDto(verifiedNumber, actualToken)));
    }

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
