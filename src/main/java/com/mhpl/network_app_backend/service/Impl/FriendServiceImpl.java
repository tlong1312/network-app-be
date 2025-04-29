package com.mhpl.network_app_backend.service.Impl;


import com.mhpl.network_app_backend.dto.UserDTO;
import com.mhpl.network_app_backend.entity.Friend;
import com.mhpl.network_app_backend.entity.Status;
import com.mhpl.network_app_backend.map.UserMapper;
import com.mhpl.network_app_backend.repository.FriendRepository;
import com.mhpl.network_app_backend.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    private FriendRepository friendRepository;

    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public void sendFriendRequest(int userId, int friendId) {
        if(friendRepository.existsByUserIdAndFriendId(userId, friendId)) {
            throw new IllegalArgumentException("Yêu cầu kết bạn đã tồn tại");
        }
        Friend friend = new Friend(userId, friendId, Status.pending);
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
    public List<UserDTO> getFriends(int userId) {
        return friendRepository.findAcceptedFriends(userId).stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }
}
