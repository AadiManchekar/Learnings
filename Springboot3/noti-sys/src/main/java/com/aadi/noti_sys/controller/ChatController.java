package com.aadi.noti_sys.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.aadi.noti_sys.model.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {

    // Sends chat messages to everyone in the chat room
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        log.info("Message sent: " + chatMessage.getContent());
        return chatMessage; 
    }

    // Adds a new user to the chat session
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        log.info("User added: " + chatMessage.getSender());
        return chatMessage; 
    }
}