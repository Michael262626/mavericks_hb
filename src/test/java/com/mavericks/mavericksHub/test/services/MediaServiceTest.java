package com.mavericks.mavericksHub.test.services;


import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.mavericks.mavericksHub.dtos.request.LikeRequest;
import com.mavericks.mavericksHub.dtos.request.UploadMediaFileRequest;
import com.mavericks.mavericksHub.dtos.responses.UpdateMediaResponse;
import com.mavericks.mavericksHub.dtos.responses.UploadMediaResponse;
import com.mavericks.mavericksHub.models.Category;
import com.mavericks.mavericksHub.models.Media;
import com.mavericks.mavericksHub.models.MediaResponse;
import com.mavericks.mavericksHub.services.interfaces.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.mavericks.mavericksHub.models.Category.*;
import static com.mavericks.mavericksHub.test.Utils.TEST_VIDEO_LOCATION;
import static com.mavericks.mavericksHub.test.Utils.buildUploadMediaRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;
    @Test
    @Sql(scripts = {"/db/data.sql"})
    void uploadMediaTest(){
        String location = "C:\\Users\\DELL\\java idea projects\\Mavericks_hub\\Mavericks_hub\\src\\main\\java\\com\\mavericks\\mavericksHub\\assets\\java.jpeg";
        Path path = Paths.get(location);
        try(InputStream input = Files.newInputStream(path)){
            UploadMediaFileRequest mediaRequest = new UploadMediaFileRequest();
            mediaRequest.setUserId(202L);
            MultipartFile multipartFile = new MockMultipartFile("java",input);
            mediaRequest.setMediaFile(multipartFile);
            UploadMediaResponse response = mediaService.upload(mediaRequest);
            assertThat(response.getMediaUrl()).isNotNull();
            assertThat(response).isNotNull();
        }
        catch(IOException error){
            assertNull(error);
        }
    }
    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testVideoUpload(){
        Path path = Paths.get(TEST_VIDEO_LOCATION);
        try(InputStream inputsStream = Files.newInputStream(path)){
            UploadMediaResponse response = mediaService.upload(buildUploadMediaRequest(inputsStream));
            assertThat(response.getMediaUrl()).isNotNull();
            assertThat(response).isNotNull();
        }
        catch (IOException error){
            assertNull(error);
        }
    }
    @Test
    @DisplayName("test update media files")
    @Sql(scripts = {"/db/data.sql"})
    void testUpdateMediaRequest() throws JsonPointerException {
        Category category = mediaService.getMediaById(103L).getCategory();
        assertThat(category).isEqualTo(COMEDY);
        List<JsonPatchOperation> operations = List.of(
                new ReplaceOperation(new JsonPointer("/category"),new TextNode(ROMANCE.name()))
        );
        JsonPatch updateMediaRequests = new JsonPatch(operations);
        UpdateMediaResponse response= mediaService.update(103L, updateMediaRequests);
        assertThat(response).isNotNull();
        category = mediaService.getMediaById(103L).getCategory();
        assertThat(category).isEqualTo(ROMANCE);
    }
    @Test
    @Sql(scripts = {"/db/data.sql"})
    void getMediaByIdTest(){
        Media media = mediaService.getMediaById(100L);
        assertThat(media).isNotNull();
    }
    @Test
    public void getMeediaForUserTest(){
        List<MediaResponse> responseList = mediaService.getMediaFor(200L);
        log.info("responseList--->   {}",responseList);
        assertThat(responseList).hasSize(2);
    }
}
