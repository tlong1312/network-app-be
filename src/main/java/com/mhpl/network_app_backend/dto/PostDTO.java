package com.mhpl.network_app_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDTO {
    private int id;
    private UserDTO user;
    private String content;
    private String mediaUrl;
    private LocalDateTime createdAt;
    private List<CommentDTO> comments;
    private int commentCount;
    private int likeCount;

}
