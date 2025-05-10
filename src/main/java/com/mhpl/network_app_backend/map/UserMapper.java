package com.mhpl.network_app_backend.map;


import com.mhpl.network_app_backend.dto.FriendUserDTO;
import com.mhpl.network_app_backend.dto.UserDTO;
import com.mhpl.network_app_backend.entity.Status;
import com.mhpl.network_app_backend.entity.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setCreateAt(user.getCreateAt());
        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAvatar(userDTO.getAvatar());
        user.setCreateAt(userDTO.getCreateAt());
        return user;
    }

    public static User toUser(FriendUserDTO friendUserDTO) {
        User user = new User();
        user.setId(friendUserDTO.getId());
        user.setUsername(friendUserDTO.getUsername());
        user.setEmail(friendUserDTO.getEmail());
        user.setAvatar(friendUserDTO.getAvatar());
        user.setCreateAt(friendUserDTO.getCreateAt());
        return user;
    }

    public static FriendUserDTO toFriendUserDTO(User user, String status) {
        FriendUserDTO friendUserDTO = new FriendUserDTO();
        friendUserDTO.setId(user.getId());
        friendUserDTO.setUsername(user.getUsername());
        friendUserDTO.setEmail(user.getEmail());
        friendUserDTO.setAvatar(user.getAvatar());
        friendUserDTO.setCreateAt(user.getCreateAt());
        friendUserDTO.setStatus(status != null ? Status.valueOf(status) : null);
        return friendUserDTO;
    }
}
