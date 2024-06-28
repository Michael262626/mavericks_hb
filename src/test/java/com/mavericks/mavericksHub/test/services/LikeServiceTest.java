package com.mavericks.mavericksHub.test.services;


import com.mavericks.mavericksHub.dtos.request.GetLikersRequest;
import com.mavericks.mavericksHub.dtos.request.LikeRequest;
import com.mavericks.mavericksHub.dtos.responses.GetLikersResponse;
import com.mavericks.mavericksHub.dtos.responses.LikeResponse;
import com.mavericks.mavericksHub.services.interfaces.LikeService;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class LikeServiceTest {
    @Autowired
    private LikeService likeService;
    @Test
    @Sql(scripts = {"/db/data.sql"})
    @DisplayName("test that media can be liked")
    void testMediaCanBeLiked(){
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setUserId(200L);
        likeRequest.setMediaId(100L);
        LikeResponse response = likeService.like(likeRequest);
        assertThat(response).isNotNull();
        assertThat(response.getNumberOfLikes()).isGreaterThanOrEqualTo(1L);
        assertEquals(1, response.getNumberOfLikes());
    }
    @Test
    @Sql(scripts = {"/db/data.sql"})
    @DisplayName("test that media cannot be liked twice")
    void testlikingMediaTwice(){
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setUserId(200L);
        likeRequest.setMediaId(100L);
        LikeResponse response = likeService.like(likeRequest);
        assertThat(response).isNotNull();
        assertEquals(1, response.getNumberOfLikes());
        response = likeService.like(likeRequest);
        assertEquals(0, response.getNumberOfLikes());
        response = likeService.like(likeRequest);
        assertEquals(1, response.getNumberOfLikes());

    }
    @Test
    @Sql(scripts = {"/db/data.sql"})
    @DisplayName("test that media likers can be gotten")
    void testUsersThatLikedMediaCanBeGotten(){
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setUserId(200L);
        likeRequest.setMediaId(100L);
        likeService.like(likeRequest);
        likeRequest.setUserId(201L);
        likeService.like(likeRequest);
        likeRequest.setUserId(202L);
        likeService.like(likeRequest);
        GetLikersRequest request = new GetLikersRequest();
        request.setMediaId(100L);
        GetLikersResponse response = likeService.getLikers(request);
        assertNotNull(response.getUsersList());
        assertEquals(3,response.getUsersList().size());
    }
}
