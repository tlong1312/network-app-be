package com.mhpl.network_app_backend.service.Impl;

import com.mhpl.network_app_backend.dto.UserDTO;
import com.mhpl.network_app_backend.entity.User;
import com.mhpl.network_app_backend.map.UserMapper;
import com.mhpl.network_app_backend.repository.UserRepository;
import com.mhpl.network_app_backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại!");
        }

        if (userRepository.findAll().stream()
                .anyMatch(user -> user .getEmail().equals(userDTO.getEmail()))) {
            throw new IllegalArgumentException("Email đã tồn tại!");
        }

        User user = UserMapper.toUser(userDTO);
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        if (user.getAvatar() == null || user.getAvatar().isEmpty()) {
            user.setAvatar("https://example.com/default-avatar.png");
        }
        user.setCreateAt(LocalDateTime.now());
        return UserMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(int userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public UserDTO updateUser(int userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(int userId) {

    }
    @Override
    public List<UserDTO> searchUsers(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword)
                .stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

}
