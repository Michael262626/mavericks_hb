package com.mavericks.mavericksHub.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UpdateMediaResponse {
    private String url;
    private String description;
    private Long id;
    @JsonProperty("timeCreated")
    private LocalDateTime timeCreated;
    @JsonProperty("timeUpdated")
    private LocalDateTime timeUpdated;
}
