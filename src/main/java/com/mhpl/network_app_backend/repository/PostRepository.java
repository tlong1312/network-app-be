package com.mhpl.network_app_backend.repository;

import com.mhpl.network_app_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
