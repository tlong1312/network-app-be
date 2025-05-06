package com.mhpl.network_app_backend.service;


import com.mhpl.network_app_backend.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(int userId);

    List<UserDTO> getAllUsers();

    UserDTO findByUserName(String username);

    UserDTO updateUser(int userId, UserDTO userDTO);

    void deleteUser(int userId);
    // Thêm declaration tìm kiếm
    List<UserDTO> searchUsers(String keyword);
}
