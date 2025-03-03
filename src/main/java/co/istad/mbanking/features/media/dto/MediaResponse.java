package co.istad.mbanking.features.media.dto;

import lombok.Builder;

@Builder
public record MediaResponse(

        String name, // equine
        String contentType, // png, jpeg, mp4 ..
        String uri,
        Long size,
        String extension
) {
}
