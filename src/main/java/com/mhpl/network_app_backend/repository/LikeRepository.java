package com.mhpl.network_app_backend.repository;

import com.mhpl.network_app_backend.entity.Like;
import com.mhpl.network_app_backend.entity.Post;
import com.mhpl.network_app_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findByPostAndUser(Post post, User user);
    boolean existsByPostAndUser(Post post, User user);
}
