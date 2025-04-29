package com.mhpl.network_app_backend.repository;


import com.mhpl.network_app_backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
