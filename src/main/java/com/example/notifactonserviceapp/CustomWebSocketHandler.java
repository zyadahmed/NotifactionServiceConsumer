package com.example.notifactonserviceapp;


import com.example.notifactonserviceapp.dto.NotifcationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        if (userId != null) {
            sessions.put(userId, session);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        if (userId != null) {
            sessions.remove(userId);
        }
    }
    public boolean sendMessageToUser(String userId, NotifcationDto message) throws IOException, IOException {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            String json  = objectMapper.writeValueAsString(message);

            System.out.println(json);
            session.sendMessage(new TextMessage(json));

            return  true;
        }
        return false;
    }



}
