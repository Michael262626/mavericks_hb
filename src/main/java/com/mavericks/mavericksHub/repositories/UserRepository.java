package com.mavericks.mavericksHub.repositories;

import com.mavericks.mavericksHub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email =:username")
    Optional<User> findByEmail(String username);
}
