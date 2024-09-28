package com.ittovative.otpservice.service;

import com.ittovative.otpservice.dto.OtpRequestDto;
import com.ittovative.otpservice.dto.VerifyTokenRequestDto;
import com.twilio.exception.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class OtpServiceTest {
    @Value("${twilio.verified-number}")
    private String verifiedNumber;
    private static GenericContainer redis;
    private static final int PORT = 6379;
    private static final String IMAGE = "redis";
    private RedisConnection redisConnectionMock;

    @Mock
    private RedisConnectionFactory redisConnectionFactoryMock;
    @SpyBean
    private OtpService otpService;

    @BeforeAll
    static void beforeAll() {
        redis = new GenericContainer(DockerImageName.parse(IMAGE)).withExposedPorts(PORT);
        redis.start();
    }

    @BeforeEach
    public void setUp() {
        Mockito.when(redisConnectionFactoryMock.getConnection()).thenReturn(redisConnectionMock);
    }

    @Test
    @Disabled("To avoid Free Twilio Limit")
    @DisplayName("Send ")
    void sendSuccessfulMessage() {
        String actualToken = otpService.send(new OtpRequestDto(verifiedNumber));
        Assertions.assertDoesNotThrow(
                () -> otpService.verifyToken(new VerifyTokenRequestDto(verifiedNumber, actualToken)));
    }

    @Test
    @DisplayName("Sending an unsuccessful message : Too short of a Phone Number")
    void sendMessageToAWrongPhoneNumber() {
        Assertions.assertThrows(Exception.class, () -> otpService.send(new OtpRequestDto("+1111")));
    }

    @Test
    @DisplayName("Sending an unsuccessful message : Sender and Receiver have the same Phone Number")
    void sendMessageToYourself() {
        Assertions.assertThrows(
                Exception.class, () -> otpService.send(new OtpRequestDto("+15075541680")));
    }

    @Test
    @DisplayName("Sending an unsuccessful message : Sending without a code at the start of the message")
    void sendToAnUncodedPhoneNumber() {
        Assertions.assertThrows(
                ApiException.class, () -> otpService.send(new OtpRequestDto("201007540077")));
    }
}
