package com.mavericks.mavericksHub.dtos.responses;

import lombok.Data;

@Data
public class UploadMediaResponse {
    private Long mediaId;
    private String mediaUrl;
    private String description;
}
