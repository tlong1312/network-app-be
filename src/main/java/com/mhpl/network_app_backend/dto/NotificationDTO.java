package com.mhpl.network_app_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class NotificationDTO {
    private String type;
    private String message;
    private String senderUsername;
    private int postId;

    public NotificationDTO(String type, String message, String senderUsername) {
        this.type = type;
        this.message = message;
        this.senderUsername = senderUsername;
    }

    public NotificationDTO(String type, String message, String senderUsername, int postId) {
        this.type = type;
        this.message = message;
        this.senderUsername = senderUsername;
        this.postId = postId;
    }
}
