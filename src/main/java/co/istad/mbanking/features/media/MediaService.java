package co.istad.mbanking.features.media;

import co.istad.mbanking.features.media.dto.MediaResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    MediaResponse uploadSingle(MultipartFile file, String folderName);

    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);

    MediaResponse loadMediaByName(@RequestParam String name, String folderName);

    MediaResponse deleteMediaByName(String id, String folderName);
}
