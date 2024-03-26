package com.ilovegogi.VoiceFinder.global.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/api/redis-test")
    public String testRedisConnection() {
        String testKey = "testKey";
        String testValue = "Hello, Redis!";
        redisTemplate.opsForValue().set(testKey, testValue);
        String retrievedValue = redisTemplate.opsForValue().get(testKey);
        if (testValue.equals(retrievedValue)) {
            return "Redis connection is working!";
        } else {
            return "Redis connection test failed.";
        }
    }
}

