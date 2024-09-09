package com.example.notifactonserviceapp.config;

import com.example.notifactonserviceapp.CustomHandshakeInterceptor;
import com.example.notifactonserviceapp.CustomWebSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final CustomHandshakeInterceptor customHandshakeInterceptor;
    private final CustomWebSocketHandler customWebSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.
                addHandler(customWebSocketHandler,"/ws")
                .setAllowedOrigins("*").addInterceptors(customHandshakeInterceptor);


    }
}
