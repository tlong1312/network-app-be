package com.mhpl.network_app_backend.map;

import com.mhpl.network_app_backend.dto.StoryDTO;
import com.mhpl.network_app_backend.entity.Story;

import java.util.List;
import java.util.stream.Collectors;

public class StoryMapper {
    public static StoryDTO toStoryDTO(Story story) {
        return new StoryDTO(
                story.getId(),
                story.getUser().getUsername(),
                story.getUser().getAvatar(),
                story.getImages().stream()
                        .map(StoryImageMapper::toStoryImageDTO)
                        .collect(Collectors.toList())
        );
    }
    public static Story toStory(StoryDTO storyDTO) {
        Story story = new Story();
        story.setId(storyDTO.getId());
        story.setImages(
                storyDTO.getImages().stream()
                        .map(StoryImageMapper::toStoryImage)
                        .collect(Collectors.toList())
        );
        return story;
    }
}
