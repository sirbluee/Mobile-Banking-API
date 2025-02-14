package co.istad.mbanking.features.user;

import co.istad.mbanking.base.BasedMessage;
import co.istad.mbanking.features.user.dto.UserCreateRequest;
import co.istad.mbanking.features.user.dto.UserDetailsReponse;
import co.istad.mbanking.features.user.dto.UserResponse;
import co.istad.mbanking.features.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // get all users
    @GetMapping
    List<UserResponse> findList() {

        return userService.findList();
    }

    // create new user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {

        userService.createNew(userCreateRequest);
    }

    // update user basic info
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }


    // get user details info
    @GetMapping("{uuid}")
    UserDetailsReponse findByUuid(@PathVariable String uuid) {

        return userService.findByUuid(uuid);
    }

    // get user by uuid
    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByUuid(@PathVariable String uuid) {

        userService.deleteByUuid(uuid);
    }

    // block user endpoint
    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid) {

        return userService.blockByUuid(uuid);

    }
}
