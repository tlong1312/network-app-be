package com.mhpl.network_app_backend.service;



import com.mhpl.network_app_backend.dto.UserDTO;

import java.util.List;

public interface FriendService {

    void sendFriendRequest(int userId, int friendId);

    void acceptFriendRequest(int userId, int friendId);

    void rejectFriendRequest(int userId, int friendId);

    List<UserDTO> getFriends(int userId);
}
