package com.mavericks.mavericksHub.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Setter
@Getter
@Entity
@ToString
@Table(name="media")
public class Media{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;
    @Enumerated(value = STRING)
    private Category category;
    @Setter(AccessLevel.NONE)
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using= LocalDateTimeDeserializer.class)
    private LocalDateTime timeCreated;
    @Setter(AccessLevel.NONE)
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using= LocalDateTimeDeserializer.class)
    private LocalDateTime timeUpdated;
    private Long uploaderId;
    @ManyToOne
    private User user;
    @PrePersist
    private void setTimeCreated(){
        this.timeCreated=LocalDateTime.now();
    }
    @PreUpdate
    private void setTimeUpdated(){
        this.timeUpdated=LocalDateTime.now();
    }
}
