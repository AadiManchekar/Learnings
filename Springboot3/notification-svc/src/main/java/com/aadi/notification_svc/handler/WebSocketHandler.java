package com.aadi.notification_svc.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.aadi.notification_svc.service.DriverConnectionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    
    private final DriverConnectionService driverConnectionService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String driverID = session.getUri().getQuery().split("=")[1]; // URL format: ws://server/ws?driverID=123
        driverConnectionService.addConnection(driverID, session);
        log.info("Driver connected: {}", driverID);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        log.info("Received message: {}", payload);
        // Example: {"driverID":"123", "latitude":12.34, "longitude":56.78}
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String driverID = driverConnectionService.removeConnection(session);
        log.info("Driver disconnected: {}", driverID);
    }
}
