package com.mhpl.network_app_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private LocalDateTime createAt;
}
