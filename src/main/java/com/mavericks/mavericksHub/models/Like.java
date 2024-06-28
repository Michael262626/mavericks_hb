package com.mavericks.mavericksHub.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@ToString
@Table(name="likes")
public class Like{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Media media;
    @Setter( AccessLevel.NONE)
    private LocalDateTime timeLiked;
    @PrePersist
    private void setTimeLiked(){
        this.timeLiked= LocalDateTime.now();
    }

}
