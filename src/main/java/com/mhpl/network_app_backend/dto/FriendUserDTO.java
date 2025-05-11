package com.mhpl.network_app_backend.dto;

import com.mhpl.network_app_backend.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendUserDTO {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private String avatar;
    private LocalDateTime createAt;
    private Status status;
}
