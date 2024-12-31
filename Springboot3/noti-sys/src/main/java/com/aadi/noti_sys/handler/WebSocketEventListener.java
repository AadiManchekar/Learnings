package com.aadi.noti_sys.handler;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.aadi.noti_sys.model.ChatMessage;
import com.aadi.noti_sys.model.MessageType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messagingTemplate;

    // Listens for when a WebSocket session disconnects
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            log.info("User Disconnected : " + username);

            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();

            // Send the leave message to all clients subscribed to the public topic
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}