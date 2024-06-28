package com.mavericks.mavericksHub.services.interfaces;

import com.mavericks.mavericksHub.dtos.request.GetLikersRequest;
import com.mavericks.mavericksHub.dtos.request.LikeRequest;
import com.mavericks.mavericksHub.dtos.responses.GetLikersResponse;
import com.mavericks.mavericksHub.dtos.responses.LikeResponse;

public interface LikeService {
    LikeResponse like(LikeRequest likeRequest);
    GetLikersResponse getLikers(GetLikersRequest request);
}
