package com.mavericks.mavericksHub.services.implementations;

import com.mavericks.mavericksHub.dtos.request.GetLikersRequest;
import com.mavericks.mavericksHub.dtos.request.LikeRequest;
import com.mavericks.mavericksHub.dtos.responses.GetLikersResponse;
import com.mavericks.mavericksHub.dtos.responses.LikeResponse;
import com.mavericks.mavericksHub.exception.MediaUpdateFailedException;
import com.mavericks.mavericksHub.models.Like;
import com.mavericks.mavericksHub.models.Media;
import com.mavericks.mavericksHub.models.User;
import com.mavericks.mavericksHub.repositories.LikeRepository;
import com.mavericks.mavericksHub.services.interfaces.LikeService;
import com.mavericks.mavericksHub.services.interfaces.MediaService;
import com.mavericks.mavericksHub.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MavericksLikeService implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserService userService;
    public LikeResponse like(LikeRequest likeRequest){
        checkMediaExistence(likeRequest.getMediaId());
        checkUserExistence(likeRequest.getUserId());
        boolean isLiked = checkIfUserLikedMedia(likeRequest);
        if(isLiked) return unlikeMedia(likeRequest);
        return likeMedia(likeRequest);
    }
    private LikeResponse likeMedia(LikeRequest likeRequest) {
        Like like = new Like();
        like.setUser(userService.findUserById(likeRequest.getUserId()));
        like.setMedia(mediaService.getMediaById(likeRequest.getMediaId()));
        likeRepository.save(like);
        Long mediaLikes= likeRepository.countAllByMedia(like.getMedia());
        LikeResponse response = new LikeResponse();
        response.setNumberOfLikes(mediaLikes);
        return response;
    }

    private LikeResponse unlikeMedia(LikeRequest likeRequest) {
        Media media = mediaService.getMediaById(likeRequest.getMediaId());
        List<Like> likes =likeRepository.findByMedia(media)
                .stream().filter(likeGotten ->likeGotten.getMedia().equals(media) &&
                        Objects.equals(likeGotten.getUser().getId(), likeRequest.getUserId())).toList();
        likeRepository.delete(likes.getFirst());
        Long mediaLikes= likeRepository.countAllByMedia(media);
        LikeResponse response = new LikeResponse();
        response.setNumberOfLikes(mediaLikes);
        return response;
    }

    private boolean checkIfUserLikedMedia(LikeRequest likeRequest){
        List<Like> likes = likeRepository.findByMedia(mediaService.getMediaById(likeRequest.getMediaId()));
       return likes.stream().filter(like-> Objects.equals(like.getUser()
                .getId(), likeRequest.getUserId())).toList().isEmpty();
    }
    @Override
    public GetLikersResponse getLikers(GetLikersRequest request){
        checkMediaExistence(request.getMediaId());
        Media media = mediaService.getMediaById(request.getMediaId());
        List<Like> likesList = likeRepository.findByMedia(media);
        List<User> users = new ArrayList<>();
        likesList.forEach(like -> users.add(like.getUser()));
        GetLikersResponse response = new GetLikersResponse();
        response.setUsersList(users);
        return response;
    }

    private void checkUserExistence(Long userId) {
        Optional<User> userFound = Optional.ofNullable(userService.findUserById(userId));
        if(userFound.isEmpty())throw new MediaUpdateFailedException("media not found");
    }
    private void checkMediaExistence(Long mediaId) {
        Optional<Media> mediaFound = Optional.ofNullable(mediaService.getMediaById(mediaId));
        if(mediaFound.isEmpty())throw new MediaUpdateFailedException("media not found");
    }
}
