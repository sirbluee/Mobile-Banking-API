package co.istad.mbanking.features.user;

import co.istad.mbanking.base.BasedMessage;
import co.istad.mbanking.features.user.dto.UserCreateRequest;
import co.istad.mbanking.features.user.dto.UserDetailsReponse;
import co.istad.mbanking.features.user.dto.UserResponse;
import co.istad.mbanking.features.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService{

    void createNew(UserCreateRequest userCreateRequest);

    UserResponse updateByUuid (String uuid, UserUpdateRequest userUpdateRequest);

    UserDetailsReponse findByUuid(String uuid);

    BasedMessage blockByUuid(String uuid);

    void deleteByUuid(String uuid);

    List<UserResponse> findList();
}
