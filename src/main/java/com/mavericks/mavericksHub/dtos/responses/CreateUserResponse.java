package com.mavericks.mavericksHub.dtos.responses;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserResponse{
    private String id;
    private String email;
    private String message;
}
