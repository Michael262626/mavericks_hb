package com.mavericks.mavericksHub.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MediaResponse {
    private Long id;
    private String url;
    private String description;
    private Category category;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private LocalDateTime timeCreated;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private LocalDateTime timeUpdated;
    private UserResponse uploader;
}
