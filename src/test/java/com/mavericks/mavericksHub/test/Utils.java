package com.mavericks.mavericksHub.test;

import com.mavericks.mavericksHub.dtos.request.UploadMediaFileRequest;
import com.mavericks.mavericksHub.models.Category;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public final static String TEST_VIDEO_LOCATION="C:\\Users\\DELL\\Videos\\Facebook_1690276956036.mp4";
    public static UploadMediaFileRequest buildUploadMediaRequest(InputStream streams) throws IOException {
        UploadMediaFileRequest request = new UploadMediaFileRequest();
        MultipartFile video1 = new MockMultipartFile("short video drums",streams);
        request.setMediaFile(video1);
        request.setUserId(201L);
        request.setCategory(Category.ACTION);
        return request;
    }
}