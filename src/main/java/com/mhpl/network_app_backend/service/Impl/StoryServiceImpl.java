package com.mhpl.network_app_backend.service.Impl;

import com.mhpl.network_app_backend.dto.StoryDTO;
import com.mhpl.network_app_backend.dto.StoryImageDTO;
import com.mhpl.network_app_backend.entity.Story;
import com.mhpl.network_app_backend.entity.StoryImage;
import com.mhpl.network_app_backend.entity.User;
import com.mhpl.network_app_backend.map.StoryImageMapper;
import com.mhpl.network_app_backend.map.StoryMapper;
import com.mhpl.network_app_backend.repository.StoryImageRepository;
import com.mhpl.network_app_backend.repository.StoryRepository;
import com.mhpl.network_app_backend.service.StoryService;
import com.mhpl.network_app_backend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final StoryImageRepository storyImageRepository;
    private final UserRepository userRepository;

    public StoryServiceImpl(StoryRepository storyRepository, StoryImageRepository storyImageRepository, UserRepository userRepository) {
        this.storyRepository = storyRepository;
        this.storyImageRepository = storyImageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void postStory(StoryImageDTO storyImageDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Story story = storyRepository.findAll().stream()
                .filter(s -> s.getUser().getId() == user.getId())
                .findFirst()
                .orElse(null);
        if (story == null) {
            story = new Story();
            story.setUser(user);
            story = storyRepository.save(story);
        }
        StoryImage storyImage = StoryImageMapper.toStoryImage(storyImageDTO);
        storyImage.setStory(story);
        storyImageRepository.save(storyImage);
    }

    @Override
    public List<StoryDTO> getAllStories() {
        return storyRepository.findAll().stream()
                .map(StoryMapper::toStoryDTO)
                .collect(Collectors.toList());
    }
}
