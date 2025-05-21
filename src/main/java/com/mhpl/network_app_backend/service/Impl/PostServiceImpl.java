package com.mhpl.network_app_backend.service.Impl;

import com.mhpl.network_app_backend.dto.PostDTO;
import com.mhpl.network_app_backend.entity.Like;
import com.mhpl.network_app_backend.entity.Post;
import com.mhpl.network_app_backend.entity.User;
import com.mhpl.network_app_backend.map.PostMapper;
import com.mhpl.network_app_backend.repository.LikeRepository;
import com.mhpl.network_app_backend.repository.PostRepository;
import com.mhpl.network_app_backend.repository.UserRepository;
import com.mhpl.network_app_backend.service.PostService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = PostMapper.toPost(postDTO);
        post.setUser(user);
        post.setContent(post.getContent());
        post.setMediaUrl(post.getMediaUrl());
        post.setCreatedAt(LocalDateTime.now());
        post.setCommentCount(0);
        post.setLikeCount(0);
        return PostMapper.toPostDTO(postRepository.save(post), false);
    }

    @Override
    public List<PostDTO> getPosts() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(post -> {
                    boolean isLiked = likeRepository.existsByPostAndUser(post, userRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("User not found")));
                    return PostMapper.toPostDTO(post, isLiked);
                })
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO updatePost(int postId, PostDTO postDTO) throws AccessDeniedException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!post.getUser().getUsername().equals(username) && !user.getRole().equals("ROLE_ADMIN")) {
            throw new AccessDeniedException("Bạn không có quyền update bài viết này");
        }

        if (postDTO.getContent() != null) {
            post.setContent(postDTO.getContent());
        }
        if (postDTO.getMediaUrl() != null) {
            post.setMediaUrl(postDTO.getMediaUrl());
        }
        boolean isLiked = likeRepository.existsByPostAndUser(post, user);

        return PostMapper.toPostDTO(postRepository.save(post), isLiked);

    }

    @Override
    public void deletePost(int postId) throws AccessDeniedException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!post.getUser().getUsername().equals(username) && !user.getRole().equals("ROLE_ADMIN")) {
            throw new AccessDeniedException("Bạn không có quyền update bài viết này");
        }


        postRepository.delete(post);
    }

    @Override
    public void likePost(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + postId + " not found"));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Like> existingLike = likeRepository.findByPostAndUser(post, user);


        if (!existingLike.isEmpty()) {

            likeRepository.deleteAll(existingLike);
            post.setLikeCount(post.getLikeCount() - existingLike.size());
            post.setIsLiked(false);
        } else {

            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(like);
            post.setLikeCount(post.getLikeCount() + 1);
            post.setIsLiked(true);
        }

        postRepository.save(post);
    }
    @Override
    public List<PostDTO> searchPosts(String keyword) {
        return postRepository.findByContentContainingIgnoreCase(keyword)
                .stream()
                .map(post -> PostMapper.toPostDTO(post, false))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByUserId(int userId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(post -> {
                    boolean isLiked = likeRepository.existsByPostAndUser(post, currentUser);
                    return PostMapper.toPostDTO(post, isLiked);
                })
                .collect(Collectors.toList());
    }

}

