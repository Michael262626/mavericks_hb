package com.mavericks.mavericksHub.services.interfaces;


import com.github.fge.jsonpatch.JsonPatch;
import com.mavericks.mavericksHub.dtos.request.UpdateMediaRequest;
import com.mavericks.mavericksHub.dtos.request.UploadMediaFileRequest;
import com.mavericks.mavericksHub.dtos.responses.UpdateMediaResponse;
import com.mavericks.mavericksHub.dtos.responses.UploadMediaResponse;
import com.mavericks.mavericksHub.models.Media;
import com.mavericks.mavericksHub.models.MediaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MediaService {
    UploadMediaResponse upload(UploadMediaFileRequest multipartFile);

    Media getMediaById(long mediaId);

    UpdateMediaResponse update(Long mediaId, JsonPatch updateRequest);

    List<MediaResponse> getMediaFor(long l);
}
