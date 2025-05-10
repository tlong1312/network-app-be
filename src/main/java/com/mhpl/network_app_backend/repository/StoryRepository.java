package com.mhpl.network_app_backend.repository;

import com.mhpl.network_app_backend.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Integer> {
}
