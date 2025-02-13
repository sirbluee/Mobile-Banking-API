package co.istad.mbanking.features.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserCreateRequest (

        @NotNull
                @Min(4)
                @Max(4)
                @Positive
        Integer pin,

        @NotBlank
                @Size(max = 40)
        String name,

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
                @Size(max = 10)
        String nationalCardId,

        @NotBlank
                @Positive
                @Size(max = 20)
        String phoneNumber,

        @NotBlank
                @Size(max = 10)
        String gender,

        @NotNull
        LocalDate dob,
        String studentId
){

}
