package com.ittovative.otpservice.service;

import com.ittovative.otpservice.constant.RedisUtil;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RedisVerificationService implements VerificationService {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisVerificationService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void setUserToken(String userPhone, String token) {
    redisTemplate.opsForValue().set(userPhone, token);
    redisTemplate.expire(userPhone, RedisUtil.EXPIRY_DATE_IN_MIN, TimeUnit.MINUTES);
  }

  @Override
  public void validateUserToken(String userPhone, String receivedToken) throws BadRequestException {
    String actualToken = (String) redisTemplate.opsForValue().get(userPhone);
    if (actualToken == null)
      throw new NoSuchElementException("This phone did receive a token before or it got expired!");
    if (!actualToken.equals(receivedToken)) throw new BadRequestException("Invalid token!");
    redisTemplate.opsForValue().getAndDelete(userPhone);
  }
}
