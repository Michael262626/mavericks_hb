package com.mavericks.mavericksHub.dtos.request;

import com.mavericks.mavericksHub.models.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadMediaFileRequest {
    private MultipartFile mediaFile;
    private String description;
    private Category category;
    private Long userId;
}
