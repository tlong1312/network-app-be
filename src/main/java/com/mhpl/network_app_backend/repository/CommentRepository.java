package com.mhpl.network_app_backend.repository;


import com.mhpl.network_app_backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(int postId);
    List<Comment> findByUserId(int userId);
    List<Comment> findByPostIdAndUserId(int postId, int userId);
    long countByPostId(int postId);
    void deleteByPostId(int postId);
}
