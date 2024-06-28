package com.mavericks.mavericksHub.repositories;

import com.mavericks.mavericksHub.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("SELECT m FROM Media m where  m.uploaderId=:userId")
    List<Media> findAllMediaFor(long userId);
}
