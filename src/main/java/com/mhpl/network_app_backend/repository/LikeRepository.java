package com.mhpl.network_app_backend.repository;

import com.mhpl.network_app_backend.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}
