package com.mhpl.network_app_backend.repository;

import com.mhpl.network_app_backend.entity.Friend;
import com.mhpl.network_app_backend.entity.Status;
import com.mhpl.network_app_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

    boolean existsByUserIdAndFriendId(int userId, int friendId);

    Optional<Friend> findByUserIdAndFriendId(int userId, int friendId);

    @Query("SELECT f FROM Friend f WHERE f.friendId = :userId AND f.status = 'pending'")
    List<Friend> findPendingRequestsForRecipient(@Param("userId") int userId);

    @Query("SELECT u FROM User u JOIN Friend f ON " +
            "(f.userId = u.id AND f.friendId = :userId OR f.friendId = u.id AND f.userId = :userId) " +
            "WHERE f.status = 'accepted'")
    List<User> findAcceptedFriends(@Param("userId") int userId);

    @Query("SELECT u FROM User u WHERE u.id != :userId AND u.id NOT IN " +
            "(SELECT CASE WHEN f.userId = :userId THEN f.friendId ELSE f.userId END " +
            "FROM Friend f WHERE (f.userId = :userId OR f.friendId = :userId) AND f.status IN ('accepted', 'pending'))")
    List<User> findUsersNotFriends(@Param("userId") int userId);
}
