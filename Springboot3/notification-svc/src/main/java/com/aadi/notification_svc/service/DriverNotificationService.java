package com.aadi.notification_svc.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverNotificationService {

    private final RedisService redisService;


    public void notifyDrivers(List<String> driverIDs, String message) {
        for (String driverID : driverIDs) {
            try {
                // Retrieve session ID from Redis
                String sessionId = redisService.getSession(driverID);

                if (sessionId != null) {
                    WebSocketSession session = redisService.getWebSocketSession(sessionId);
                    if (session != null && session.isOpen()) {
                        session.sendMessage(new TextMessage(message));
                        log.info("Message sent to driver {}", driverID);
                    } else {
                        log.warn("Session for driver {} is not open", driverID);
                    }
                } else {
                    log.warn("Driver {} is not connected", driverID);
                }
            } catch (IOException e) {
                log.error("Failed to send message to driver {}: {}", driverID, e.getMessage());
            }
        }
    }
}
