package com.mavericks.mavericksHub.dtos.request;


import com.mavericks.mavericksHub.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateMediaRequest {
    private Long mediaId;
    private String description;
    private Category category;
}
