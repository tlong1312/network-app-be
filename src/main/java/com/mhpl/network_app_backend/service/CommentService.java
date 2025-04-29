package com.mhpl.network_app_backend.service;

import com.mhpl.network_app_backend.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, int postId, int userId);

    List<CommentDTO> getAllCommentsByPostId(int postId);

    CommentDTO updateComment(int commentId, CommentDTO commentDTO);

    void deleteComment(int id);
}
