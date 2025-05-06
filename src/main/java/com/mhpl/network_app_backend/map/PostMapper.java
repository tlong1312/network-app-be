package com.mhpl.network_app_backend.map;



import com.mhpl.network_app_backend.dto.PostDTO;
import com.mhpl.network_app_backend.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDTO toPostDTO(Post post, boolean isLiked) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setMediaUrl(post.getMediaUrl());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setCommentCount(post.getCommentCount());
        postDTO.setLikeCount(post.getLikeCount());
        postDTO.setUser(UserMapper.toUserDTO(post.getUser()));
        postDTO.setComments(post.getComments().stream()
                .map(CommentMapper::toCommentDTO)
                .collect(Collectors.toList()));
        postDTO.setLiked(isLiked);
        return postDTO;
    }

    public static Post toPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setContent(postDTO.getContent());
        post.setMediaUrl(postDTO.getMediaUrl());
        // Do not set user, createdAt, commentCount, or likeCount here
        return post;
    }
}
