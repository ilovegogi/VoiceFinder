package com.ilovegogi.VoiceFinder.global.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private final RedisTemplate<String, String> redisTemplate;

    public RedisUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setRefreshToken(String key, String value, long duration) {
        try {
            redisTemplate.opsForValue().set(key, value, duration, TimeUnit.MILLISECONDS);
            logger.info("Successfully set key: {}, value: {}, duration: {}", key, value, duration);
        } catch (Exception e) {
            logger.error("Failed to set key: {}, value: {}, duration: {}", key, value, duration, e);
        }
    }

    public String getRefreshToken(String key) {
        try {
            String value = redisTemplate.opsForValue().get(key);
//            System.out.println("value = " + value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get value for key: {}", key, e);
            return null;
        }
    }
}
