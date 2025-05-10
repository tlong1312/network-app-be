package com.mhpl.network_app_backend.service.Impl;


import com.mhpl.network_app_backend.dto.FriendUserDTO;
import com.mhpl.network_app_backend.dto.UserDTO;
import com.mhpl.network_app_backend.entity.Friend;
import com.mhpl.network_app_backend.entity.Status;
import com.mhpl.network_app_backend.entity.User;
import com.mhpl.network_app_backend.map.UserMapper;
import com.mhpl.network_app_backend.repository.FriendRepository;
import com.mhpl.network_app_backend.repository.UserRepository;
import com.mhpl.network_app_backend.service.FriendService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    private FriendRepository friendRepository;
    private UserRepository userRepository;

    public FriendServiceImpl(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void sendFriendRequest(int userId, int friendId) {
        if(friendRepository.existsByUserIdAndFriendId(userId, friendId)) {
            throw new IllegalArgumentException("Yêu cầu kết bạn đã tồn tại");
        }
        Friend friend = new Friend(userId, friendId, Status.pending);
        friend.setCreatedAt(LocalDateTime.now());
        friendRepository.save(friend);
    }

    @Override
    public void acceptFriendRequest(int userId, int friendId) {
        Friend friend = friendRepository.findByUserIdAndFriendId(friendId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy yêu cầu kết bạn!"));

        friend.setStatus(Status.accepted);
        friendRepository.save(friend);

    }

    @Override
    public void rejectFriendRequest(int userId, int friendId) {
        Friend friend = friendRepository.findByUserIdAndFriendId(friendId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy yêu cầu kết bạn!"));
        friend.setStatus(Status.rejected);
        friendRepository.save(friend);
    }

    @Override
    public void unfriend(int userId, int friendId) {
        Friend friend = friendRepository.findByUserIdAndFriendId(userId, friendId)
                .or(() -> friendRepository.findByUserIdAndFriendId(friendId, userId))
                .orElseThrow(() -> new IllegalArgumentException("Friendship not found"));

        friendRepository.delete(friend);
    }

    @Override
    public List<UserDTO> getFriends(int userId) {
        return friendRepository.findAcceptedFriends(userId)
                .stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendUserDTO> getUsersNotFriends(int userId) {
        return friendRepository.findUsersNotFriends(userId)
                .stream()
                .map(user -> {
                    String status = friendRepository.findByUserIdAndFriendId(userId, user.getId())
                            .map(friend -> friend.getStatus().name())
                            .orElse(null);
                    return UserMapper.toFriendUserDTO(user, status);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendUserDTO> getFriendRequests(int userId) {
        return friendRepository.findPendingRequestsForRecipient(userId)
                .stream()
                .map(friend -> {
                    User user = userRepository.findById(friend.getUserId())
                            .orElseThrow(() -> new IllegalArgumentException("User not found"));
                    return UserMapper.toFriendUserDTO(user, "pending");
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getFriendshipStatus(int userId, int friendId) {
        return friendRepository.findByUserIdAndFriendId(userId, friendId)
                .or(() -> friendRepository.findByUserIdAndFriendId(friendId, userId)) // Check reverse direction
                .map(friend -> {
                    boolean isReceiver = friend.getFriendId() == userId;
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", friend.getStatus().name());
                    result.put("isReceiver", isReceiver);
                    return result;
                })
                .orElseGet(() -> Map.of(
                        "status", "none",
                        "isReceiver", false
                ));
    }
}
