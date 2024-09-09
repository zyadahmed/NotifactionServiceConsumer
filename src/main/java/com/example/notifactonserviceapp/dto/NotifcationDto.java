package com.example.notifactonserviceapp.dto;

import com.example.notifactonserviceapp.enums.NotificationType;
import lombok.Data;


@Data
public class NotifcationDto {
    private int userId;
    private NotificationType type;
    private Long senderAccountId;
    private Long receiverAccountId;
    private String senderName;
    private String receiverName;
    private float amount;



}
