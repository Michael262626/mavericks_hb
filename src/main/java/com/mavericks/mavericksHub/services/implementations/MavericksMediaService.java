package com.mavericks.mavericksHub.services.implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mavericks.mavericksHub.dtos.request.UploadMediaFileRequest;
import com.mavericks.mavericksHub.dtos.responses.UpdateMediaResponse;
import com.mavericks.mavericksHub.dtos.responses.UploadMediaResponse;
import com.mavericks.mavericksHub.exception.MediaUpdateFailedException;
import com.mavericks.mavericksHub.exception.UserNotExistException;
import com.mavericks.mavericksHub.models.Media;
import com.mavericks.mavericksHub.models.MediaResponse;
import com.mavericks.mavericksHub.models.User;
import com.mavericks.mavericksHub.repositories.MediaRepository;
import com.mavericks.mavericksHub.services.interfaces.MediaService;
import com.mavericks.mavericksHub.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class MavericksMediaService implements MediaService {
    private final MediaRepository mediaRepo;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UploadMediaResponse upload(UploadMediaFileRequest request){
        User user = userService.findUserById(request.getUserId());
        try{
            Map<?,?> response = cloudinary.uploader()
                    .upload(request.getMediaFile().getBytes(),
                            ObjectUtils.asMap("resource_type","auto"));
            Media media = new Media();
            media.setUrl(response.get("url").toString());
            media.setDescription(request.getDescription());
            media.setUser(user);
            media = mediaRepo.save(media);
            return modelMapper.map(media,UploadMediaResponse.class);
        }
        catch(IOException error){
            throw new RuntimeException(error);
        }
    }
    public Media getMediaById(long mediaId) {
        return mediaRepo.findById(mediaId)
                .orElseThrow(()-> new UserNotExistException(String.format
                        ("media with id %d does not exist",mediaId)));
    }

    @Override
    public UpdateMediaResponse update(Long mediaId, JsonPatch jsonPatch) {
        try {
            Media media = getMediaById(mediaId);//get target object
            ObjectMapper mapper = new ObjectMapper();
            JsonNode mediaNode = mapper.convertValue(media, JsonNode.class);//convert object to jsonNode
            mediaNode = jsonPatch.apply(mediaNode);//apply to media node
            media=mapper.convertValue(mediaNode, Media.class);//convert to media
            mediaRepo.save(media);
            return modelMapper.map(media, UpdateMediaResponse.class);
        }
        catch (JsonPatchException exception){
            throw new MediaUpdateFailedException(exception.getMessage());
        }
    }

    @Override
    public List<MediaResponse> getMediaFor(long userId) {
        List<Media> media = mediaRepo.findAllMediaFor(userId);
        return media.stream().map(mediaItem->modelMapper.map(mediaItem,MediaResponse.class)).toList();
    }

}
