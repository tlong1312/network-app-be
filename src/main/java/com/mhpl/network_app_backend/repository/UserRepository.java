package com.mhpl.network_app_backend.repository;


import com.mhpl.network_app_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    // Tìm kiếm người dùng theo username (ignore case)
    List<User> findByUsernameContainingIgnoreCase(String keyword);
}
