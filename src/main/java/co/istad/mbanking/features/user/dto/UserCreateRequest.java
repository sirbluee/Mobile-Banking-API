package co.istad.mbanking.features.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UserCreateRequest (

        @NotNull
                @Max(9999)
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
        String confirmedPassword,

        @NotBlank
                @Size(max = 10)
        String nationalCardId,

        @NotBlank
        @Size(max = 20)
        String phoneNumber,

        @NotBlank
                @Size(max = 10)
        String gender,

        @NotNull
        LocalDate dob,

        String studentIdCard,

        @NotEmpty
        List<RoleRequest> roles
){

}
