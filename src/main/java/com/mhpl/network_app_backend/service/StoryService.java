package com.mhpl.network_app_backend.service;

import com.mhpl.network_app_backend.dto.StoryDTO;
import com.mhpl.network_app_backend.dto.StoryImageDTO;

import java.util.List;

public interface StoryService {
    void postStory(StoryImageDTO storyImageDTO);
    List<StoryDTO> getAllStories();
}
