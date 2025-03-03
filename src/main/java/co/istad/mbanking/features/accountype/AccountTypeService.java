package co.istad.mbanking.features.accountype;

import co.istad.mbanking.features.accountype.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {

    List<AccountTypeResponse> findList();

    AccountTypeResponse findByAlias(String alias);
}
