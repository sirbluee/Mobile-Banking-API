package co.istad.mbanking.mapper;

import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.user.dto.UserCreateRequest;
import co.istad.mbanking.features.user.dto.UserDetailsReponse;
import co.istad.mbanking.features.user.dto.UserResponse;
import co.istad.mbanking.features.user.dto.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

// create mapstruct
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Transfer userCreateRequest to User Entity
    // SourceType = UserCreateRequest (Parameter)
    // Target = User

    //can write like this as well
    //void fromUserCreateRequest(@MappingTarget UserCreateRequest userCreateRequest, User user);

    User toUser(UserCreateRequest userCreateRequest);
    UserDetailsReponse toUserDetailsReponse(User user);

    // to handle properties update ensure not null
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
