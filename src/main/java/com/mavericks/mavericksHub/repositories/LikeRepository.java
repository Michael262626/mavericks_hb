package com.mavericks.mavericksHub.repositories;

import com.mavericks.mavericksHub.models.Like;
import com.mavericks.mavericksHub.models.Media;
import com.mavericks.mavericksHub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByMedia(Media media);
    Long countAllByMedia(Media media);
    Like findByMediaAndUser(Media media, User user);
}
