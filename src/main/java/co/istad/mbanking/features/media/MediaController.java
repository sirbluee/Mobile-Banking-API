package co.istad.mbanking.features.media;

import co.istad.mbanking.features.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestParam MultipartFile file) {

        return mediaService.uploadSingle(file, "IMAGE");
    }

    // upload multiple
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload-multiple")
    List<MediaResponse> uploadMultiple(@RequestParam List<MultipartFile> files) {
        return mediaService.uploadMultiple(files, "IMAGE");
    }

    // load media by name
    @GetMapping("/{mediaName}")
    MediaResponse getMedia(@PathVariable String mediaName) {
        return mediaService.loadMediaByName(mediaName, "IMAGE");
    }

}
