package com.mhpl.network_app_backend.service;



import com.mhpl.network_app_backend.dto.FriendUserDTO;
import com.mhpl.network_app_backend.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface FriendService {

    void sendFriendRequest(int userId, int friendId);

    void acceptFriendRequest(int userId, int friendId);

    void rejectFriendRequest(int userId, int friendId);

    void unfriend(int userId, int friendId);

    List<UserDTO> getFriends(int userId);

    List<FriendUserDTO> getUsersNotFriends(int userId);

    List<FriendUserDTO> getFriendRequests(int userId);

    Map<String, Object> getFriendshipStatus(int userId, int friendId);
}
