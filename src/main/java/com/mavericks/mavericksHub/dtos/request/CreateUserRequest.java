package com.mavericks.mavericksHub.dtos.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
}
