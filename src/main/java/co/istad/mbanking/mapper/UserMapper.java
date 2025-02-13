package co.istad.mbanking.mapper;

import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.user.dto.UserCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

// create mapstruct
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Transfer userCreateRequest to User Entity
    // SourceType = UserCreateRequest (Parameter)
    // Target = User
    User toUser(UserCreateRequest userCreateRequest);

    //can write like this as well
    //void fromUserCreateRequest(@MappingTarget UserCreateRequest userCreateRequest, User user);

}
