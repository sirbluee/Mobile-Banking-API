package co.istad.mbanking.mapper;

import co.istad.mbanking.domain.Account;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.domain.UserAccount;
import co.istad.mbanking.features.account.dto.AccountCreateRequest;
import co.istad.mbanking.features.account.dto.AccountResponse;
import co.istad.mbanking.features.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account fromAccountCreateRequestToAccount(AccountCreateRequest accountCreateRequest);

    @Mapping(source = "userAccountList", target = "user", qualifiedByName = "mapUserResponse")
    AccountResponse toAccountResponse(Account account);

    // to get user from user mapper
    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){

        return toUserResponse(userAccountList.get(0).getUser());
    }

    UserResponse toUserResponse(User user);
}
