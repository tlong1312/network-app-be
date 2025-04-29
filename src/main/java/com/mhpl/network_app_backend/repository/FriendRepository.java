package com.mhpl.network_app_backend.repository;

import com.mhpl.network_app_backend.entity.Friend;
import com.mhpl.network_app_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

    boolean existsByUserIdAndFriendId(int userId, int friendId);

    Optional<Friend> findByUserIdAndFriendId(int userId, int friendId);

    @Query("SELECT u FROM User u JOIN Friend f ON (f.userId = :userId OR f.friendId = :userId) AND f.status = 'accepted'")
    List<User> findAcceptedFriends(@Param("userId") int userId);

}
