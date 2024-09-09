package com.example.notifactonserviceapp;

import com.example.notifactonserviceapp.security.TokenService;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;


@Component
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

      @Autowired
     private TokenService tokenService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        System.out.println("Enter");
        HttpHeaders headers = request.getHeaders();
        String authorization = headers.getFirst("Authorization");
        System.out.println("Auth" + authorization);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return false;
        }

        System.out.println("before");
        String token = authorization.substring(7);  // Remove "Bearer "
        String userId = tokenService.validateTokenAndGetUserId(token);
        System.out.println(userId + "Iddddd");
        if (userId == null) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }

        attributes.put("userId", userId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
