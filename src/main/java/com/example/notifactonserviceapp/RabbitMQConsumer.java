package com.example.notifactonserviceapp;

import com.example.notifactonserviceapp.dto.NotifcationDto;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class RabbitMQConsumer {


    private final CustomWebSocketHandler handler;


    @RabbitListener(queues = "messageNotificationQueue", ackMode = "MANUAL")
    public void consume(NotifcationDto notifcationDto) {
        try {
            boolean send = handler.sendMessageToUser(String.valueOf(notifcationDto.getUserId()), notifcationDto);
            System.out.println("Success Send");
        } catch (Exception e) {
            System.out.println("fail");
        }
    }
}
