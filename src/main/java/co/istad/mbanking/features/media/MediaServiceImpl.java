package co.istad.mbanking.features.media;

import co.istad.mbanking.features.media.dto.MediaResponse;
import co.istad.mbanking.util.MediaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor // to create final contractor
@Slf4j
public class MediaServiceImpl implements MediaService {

    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUri;

    @Override
    public MediaResponse uploadSingle(MultipartFile file, String folderName) {
        // generate new unique name for file upload
        String newName = UUID.randomUUID().toString();

        // extract file extension from file upload
        String extension = MediaUtil.extractExtention(file.getOriginalFilename());
        newName = newName + "." + extension;
        log.info("New name: {}", newName);

        // copy file to server
        Path path = Paths.get(serverPath + folderName + "\\" + newName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }
        return MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(String.format("%s%s/%s", baseUri, folderName, newName))
                .build();
    }

    // multiple file
    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {

        // create empty array list, wait file uploaded file
        List<MediaResponse> mediaResponses = new ArrayList<>();

        // use loop to upload each files
        files.forEach(file -> {
            MediaResponse mediaResponse = uploadSingle(file, folderName);
            mediaResponses.add(mediaResponse);
        });
        return mediaResponses;
    }

    // load by name
    @Override
    public MediaResponse loadMediaByName(String mediaName, String folderName) {

        //create absolut path of media
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);

        try {
            Resource resource = new UrlResource(path.toUri());
            log.info("Resource: {}", resource.getFilename());

            if (!resource.exists()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Media not found");
            }
            return MediaResponse.builder()
                    .name(resource.getFilename())
                    .contentType("")
                    .extension(MediaUtil.extractExtention(resource.getFilename()))
                    .size(resource.contentLength())
                    .uri(String.format("%s%s/%s", baseUri, folderName, resource.getFilename()))
                    .build();

        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }
    }

    // delete media
    @Override
    public MediaResponse deleteMediaByName(String id, String folderName) {


        return null;
    }
}
