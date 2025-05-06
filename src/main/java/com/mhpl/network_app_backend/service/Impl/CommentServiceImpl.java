package com.mhpl.network_app_backend.service.Impl;

import com.mhpl.network_app_backend.entity.Comment;
import com.mhpl.network_app_backend.entity.Post;
import com.mhpl.network_app_backend.entity.User;
import com.mhpl.network_app_backend.map.CommentMapper;
import com.mhpl.network_app_backend.repository.CommentRepository;
import com.mhpl.network_app_backend.repository.PostRepository;
import com.mhpl.network_app_backend.repository.UserRepository;
import com.mhpl.network_app_backend.service.CommentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.mhpl.network_app_backend.dto.CommentDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post);
        comment.setUser(user);
        comment.getPost().setCommentCount(comment.getPost().getCommentCount() + 1);

        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toCommentDTO(savedComment);
    }

    @Override
    public List<CommentDTO> getAllCommentsByPostId(int postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(CommentMapper::toCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(int commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + commentId));

        comment.setContent(commentDTO.getContent());
        Comment updatedComment = commentRepository.save(comment);
        return CommentMapper.toCommentDTO(updatedComment);
    }

    @Override
    public void deleteComment(int id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}
