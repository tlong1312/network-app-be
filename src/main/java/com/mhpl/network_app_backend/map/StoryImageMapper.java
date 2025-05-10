package com.mhpl.network_app_backend.map;

import com.mhpl.network_app_backend.entity.StoryImage;
import com.mhpl.network_app_backend.dto.StoryImageDTO;

public class StoryImageMapper {
    public static StoryImageDTO toStoryImageDTO(StoryImage storyImage) {
        return new StoryImageDTO(
                storyImage.getUrl(),
                storyImage.getDuration()
        );
    }

    public static StoryImage toStoryImage(StoryImageDTO storyImageDTO) {
        StoryImage storyImage = new StoryImage();
        storyImage.setUrl(storyImageDTO.getUrl());
        storyImage.setDuration(storyImageDTO.getDuration() > 0 ? storyImageDTO.getDuration() : 5000);
        return storyImage;
    }
}
