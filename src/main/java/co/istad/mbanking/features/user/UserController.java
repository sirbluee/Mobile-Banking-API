package co.istad.mbanking.features.user;

import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.user.dto.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {

        userService.createNew(userCreateRequest);
    }

}
