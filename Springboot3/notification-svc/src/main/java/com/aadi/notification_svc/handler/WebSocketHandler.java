package com.aadi.notification_svc.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.aadi.notification_svc.service.RedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    
    private final RedisService redisService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String driverID = session.getUri().getQuery().split("=")[1];
        redisService.saveSession(driverID, session.getId());   
        log.info("Driver {} connected with session {}", driverID, session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("Received message from session {}: {}", session.getId(), message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String driverID = redisService.getSession(session.getId());
        redisService.removeSession(driverID);
        log.info("Session {} closed", session.getId());
    }
}
