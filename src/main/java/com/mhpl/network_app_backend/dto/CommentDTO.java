package com.mhpl.network_app_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private int id;
    private String content;
    private LocalDateTime createdAt;
    private UserDTO user;
}
