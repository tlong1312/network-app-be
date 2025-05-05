package com.mhpl.network_app_backend.map;


import com.mhpl.network_app_backend.dto.CurrentUserDTO;
import com.mhpl.network_app_backend.dto.UserDTO;
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

    public static User toUser(CurrentUserDTO currentUserDTO) {
        User user = new User();
        user.setUsername(currentUserDTO.getUsername());
        user.setEmail(currentUserDTO.getEmail());
        user.setAvatar(currentUserDTO.getAvatar());
        user.setCreateAt(currentUserDTO.getCreateAt());
        return user;
    }

    public static CurrentUserDTO toCurrentUserDTO(User user) {
        CurrentUserDTO currentUserDTO = new CurrentUserDTO();
        currentUserDTO.setUsername(user.getUsername());
        currentUserDTO.setEmail(user.getEmail());
        currentUserDTO.setAvatar(user.getAvatar());
        currentUserDTO.setCreateAt(user.getCreateAt());
        return currentUserDTO;
    }
}
