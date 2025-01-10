package com.aadi.notification_svc.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;
    
    private static final String DRIVER_PREFIX = "driver:";
    private static final String SESSION_PREFIX = "session:";
    
    // Save session to Redis
    public void saveSession(String driverID, String sessionId) {
        redisTemplate.opsForValue().set(DRIVER_PREFIX + driverID, sessionId, 1, TimeUnit.HOURS);
        redisTemplate.opsForValue().set(SESSION_PREFIX + sessionId, driverID, 1, TimeUnit.HOURS);
    }

    // Get session by driverID
    public String getSession(String driverID) {
        return redisTemplate.opsForValue().get(DRIVER_PREFIX + driverID);
    }

    // Remove session when disconnected
    public void removeSession(String driverID) {
        String sessionId = redisTemplate.opsForValue().get(DRIVER_PREFIX + driverID);
        if (sessionId != null) {
            redisTemplate.delete(DRIVER_PREFIX + driverID);
            redisTemplate.delete(SESSION_PREFIX + sessionId);
        }
    }

    // Get driver ID by session ID
    public String getDriver(String sessionId) {
        return redisTemplate.opsForValue().get(SESSION_PREFIX + sessionId);
    }
}