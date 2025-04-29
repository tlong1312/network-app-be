package com.mhpl.network_app_backend.config;

import com.mhpl.network_app_backend.entity.User;
import com.mhpl.network_app_backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdminAccountInit {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAccountInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if(userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            if (admin.getAvatar() == null || admin.getAvatar().isEmpty()) {
                admin.setAvatar("https://example.com/default-avatar.png");
            }
            admin.setCreateAt(LocalDateTime.now());
            userRepository.save(admin);
        }
    }
}
