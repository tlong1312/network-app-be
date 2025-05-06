package com.mhpl.network_app_backend.service;

import com.mhpl.network_app_backend.dto.PostDTO;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getPosts();

    PostDTO updatePost(int postId ,PostDTO postDTO) throws AccessDeniedException;

    void deletePost(int postId) throws AccessDeniedException;

    void likePost(int postId);
    // Thêm declaration tìm kiếm
    List<PostDTO> searchPosts(String keyword);
}
