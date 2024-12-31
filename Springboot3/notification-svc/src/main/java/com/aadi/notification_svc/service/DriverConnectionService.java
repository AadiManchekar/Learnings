package com.aadi.notification_svc.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class DriverConnectionService {
    private final ConcurrentHashMap<String, WebSocketSession> driverConnections = new ConcurrentHashMap<>();

    public void addConnection(String driverID, WebSocketSession session) {
        driverConnections.put(driverID, session);
    }

    public String removeConnection(WebSocketSession session) {
        return driverConnections.entrySet().stream()
                .filter(entry -> entry.getValue().equals(session))
                .map(entry -> {
                    driverConnections.remove(entry.getKey());
                    return entry.getKey();
                })
                .findFirst()
                .orElse(null);
    }

    public WebSocketSession getConnection(String driverID) {
        return driverConnections.get(driverID);
    }

    public boolean isDriverConnected(String driverID) {
        return driverConnections.containsKey(driverID);
    }
}
