package com.mavericks.mavericksHub.controller;

import com.mavericks.mavericksHub.dtos.request.UploadMediaFileRequest;
import com.mavericks.mavericksHub.services.interfaces.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/media")
 public class MediaController {
    private final MediaService mediaService;
    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadMedia(@ModelAttribute UploadMediaFileRequest uploadRequest){
        return ResponseEntity.status(CREATED).body(mediaService.upload(uploadRequest));
    }
    @GetMapping
    public ResponseEntity<?> getMediaForUser(@RequestParam Long userId){
        return ResponseEntity.ok(mediaService.getMediaFor(userId));

    }
}
