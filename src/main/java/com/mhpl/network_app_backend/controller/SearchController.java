package com.mhpl.network_app_backend.controller;

import com.mhpl.network_app_backend.dto.PostDTO;
import com.mhpl.network_app_backend.dto.UserDTO;
import com.mhpl.network_app_backend.service.PostService;
import com.mhpl.network_app_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final PostService postService;
    private final UserService userService;

    public SearchController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /** Tìm cả posts + users */
    @GetMapping
    public ResponseEntity<Map<String, Object>> searchAll(@RequestParam("q") String q) {
        List<PostDTO> posts = postService.searchPosts(q);
        List<UserDTO> users = userService.searchUsers(q);
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        result.put("users", users);
        return ResponseEntity.ok(result);
    }

    /** Chỉ tìm posts */
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam("q") String q) {
        return ResponseEntity.ok(postService.searchPosts(q));
    }

    /** Chỉ tìm users */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam("q") String q) {
        return ResponseEntity.ok(userService.searchUsers(q));
    }
}
