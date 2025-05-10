package com.mhpl.network_app_backend.controller;

import com.mhpl.network_app_backend.dto.FriendUserDTO;
import com.mhpl.network_app_backend.dto.UserDTO;
import com.mhpl.network_app_backend.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    private FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/send-request")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> sendFriendRequest(@RequestParam int userId, @RequestParam int friendId) {
        friendService.sendFriendRequest(userId, friendId);
        return ResponseEntity.ok("Gửi yêu cầu kết bạn thành công.");
    }

    @PostMapping("/accept-request")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam int userId, @RequestParam int friendId) {
        friendService.acceptFriendRequest(userId, friendId);
        return ResponseEntity.ok("Friend request accepted successfully.");
    }

    @PostMapping("/reject-request")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> rejectFriendRequest(@RequestParam int userId, @RequestParam int friendId) {
        friendService.rejectFriendRequest(userId, friendId);
        return ResponseEntity.ok("Friend request rejected successfully.");
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserDTO>> getFriends(@RequestParam int userId) {
        List<UserDTO> friends = friendService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/not-friends")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FriendUserDTO>> getUsersNotFriends(@RequestParam int userId) {
        List<FriendUserDTO> users = friendService.getUsersNotFriends(userId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/requests")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FriendUserDTO>> getFriendRequests(@RequestParam int userId) {
        List<FriendUserDTO> requests = friendService.getFriendRequests(userId);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/friendship-status")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> getFriendshipStatus(@RequestParam int userId, @RequestParam int friendId) {
        Map<String, Object> status = friendService.getFriendshipStatus(userId, friendId);
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/unfriend")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> unfriend(@RequestParam int userId, @RequestParam int friendId) {
        friendService.unfriend(userId, friendId);
        return ResponseEntity.ok("Unfriended successfully.");
    }
}
