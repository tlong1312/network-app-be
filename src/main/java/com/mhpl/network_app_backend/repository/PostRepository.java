package com.mhpl.network_app_backend.repository;

import com.mhpl.network_app_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByContentContainingIgnoreCase(String keyword);
    List<Post> findByUserIdOrderByCreatedAtDesc(int userId);
}
