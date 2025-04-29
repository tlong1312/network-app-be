package com.mhpl.network_app_backend.controller;

import com.mhpl.network_app_backend.dto.UserDTO;
import com.mhpl.network_app_backend.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    private FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/send-request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam int userId, @RequestParam int friendId) {
        friendService.sendFriendRequest(userId, friendId);
        return ResponseEntity.ok("Gửi yêu cầu kết bạn thành công.");
    }

    @PostMapping("/accept-request")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam int userId, @RequestParam int friendId) {
        friendService.acceptFriendRequest(userId, friendId);
        return ResponseEntity.ok("Friend request accepted successfully.");
    }

    @PostMapping("/reject-request")
    public ResponseEntity<String> rejectFriendRequest(@RequestParam int userId, @RequestParam int friendId) {
        friendService.rejectFriendRequest(userId, friendId);
        return ResponseEntity.ok("Friend request rejected successfully.");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getFriends(@RequestParam int userId) {
        List<UserDTO> friends = friendService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }
}
