package com.aadi.notification_svc.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverNotificationService {

    private final DriverConnectionService driverConnectionService;

    public void notifyDriver(String driverID, String message) {
        WebSocketSession session = driverConnectionService.getConnection(driverID);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
                log.info("Sent message to driver {}: {}", driverID, message);
            } catch (IOException e) {
                log.error("Failed to send message to driver {}: {}", driverID, e.getMessage());
            }
        }
    }
}
