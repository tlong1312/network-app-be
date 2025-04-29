package com.mhpl.network_app_backend.map;


import com.mhpl.network_app_backend.dto.CommentDTO;
import com.mhpl.network_app_backend.entity.Comment;

public class CommentMapper {
    public static CommentDTO toCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setUser(UserMapper.toUserDTO(comment.getUser()));
        return commentDTO;
    }
}
