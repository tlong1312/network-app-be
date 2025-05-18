package com.mhpl.network_app_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private LocalDateTime createdAt;
}
