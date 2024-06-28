package com.mavericks.mavericksHub.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikeRequest {
    private Long mediaId;
    private Long userId;
}
