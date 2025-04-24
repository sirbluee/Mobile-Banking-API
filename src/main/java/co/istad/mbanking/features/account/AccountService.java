package co.istad.mbanking.features.account;

import co.istad.mbanking.features.account.dto.AccountCreateRequest;
import co.istad.mbanking.features.account.dto.AccountRenameRequest;
import co.istad.mbanking.features.account.dto.AccountResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {

    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);

    void hideAccount(String actNo);

    Page<AccountResponse> findAllAccounts(int page, int size);
}
