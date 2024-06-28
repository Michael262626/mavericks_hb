package com.mavericks.mavericksHub.exception;


public class MediaUpdateFailedException extends RuntimeException{
    public MediaUpdateFailedException(String errorMessage){
        super(errorMessage);
    }
}
