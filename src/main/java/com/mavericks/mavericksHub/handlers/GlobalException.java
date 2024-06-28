package com.mavericks.mavericksHub.handlers;

import com.mavericks.mavericksHub.exception.MediaUpdateFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


//@ControllerAdvice// used fro model views controllers
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MediaUpdateFailedException.class)
    @ResponseBody
    public ResponseEntity<?> handleMediaUploadFiled(MediaUpdateFailedException error){
        return ResponseEntity.status(BAD_REQUEST).body(
                Map.of("error",error.getMessage(),"success",false));
    }
}
