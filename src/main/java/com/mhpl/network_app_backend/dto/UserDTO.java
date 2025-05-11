package com.mhpl.network_app_backend.dto;

import lombok.Data;
import org.hibernate.engine.spi.Status;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String avatar;
    private LocalDateTime createAt;
}
