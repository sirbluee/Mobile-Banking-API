package co.istad.mbanking.features.account;

import co.istad.mbanking.features.account.dto.AccountCreateRequest;
import co.istad.mbanking.features.account.dto.AccountRenameRequest;
import co.istad.mbanking.features.account.dto.AccountResponse;

public interface AccountService {

    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);
}
