package com.mhpl.network_app_backend.controller;

import com.mhpl.network_app_backend.dto.StoryDTO;
import com.mhpl.network_app_backend.dto.StoryImageDTO;
import com.mhpl.network_app_backend.service.StoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<StoryDTO>> getAllStories() {
        List<StoryDTO> stories = storyService.getAllStories();
        return ResponseEntity.ok(stories);
    }
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> postStory(@RequestBody StoryImageDTO storyImageDTO) {
        storyService.postStory(storyImageDTO);
        return new ResponseEntity<>("Tạo story thành công", HttpStatus.CREATED);
    }
}
