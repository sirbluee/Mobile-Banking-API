package co.istad.mbanking.features.user.dto;

import java.time.LocalDate;

public record UserResponse(
        String name,
        String uuid,
        String profileImage,
        String gender,
        LocalDate dob
) {
}
