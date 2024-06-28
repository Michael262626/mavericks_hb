package com.mavericks.mavericksHub.exception;

import com.mavericks.mavericksHub.models.User;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String message){
        super(message);
    }
}
