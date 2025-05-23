package co.istad.mbanking.features.account;

import co.istad.mbanking.features.account.dto.AccountCreateRequest;
import co.istad.mbanking.features.account.dto.AccountRenameRequest;
import co.istad.mbanking.features.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createAccount(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }

    // find account by actNo
    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findByActNo(actNo);
    }

    // rename
    @PutMapping("/{actNo}/rename")
    AccountResponse renameByActNo(@Valid @PathVariable String actNo, @Valid @RequestBody AccountRenameRequest accountRenameRequest) {

        return accountService.renameByActNo(actNo, accountRenameRequest);
    }

    // hide account by actNo
    @PutMapping("/{actNo}/hide")
    void hideAccountByActNo(@Valid @PathVariable String actNo) {
        accountService.hideAccount(actNo);
    }

    // get all accounts
    @GetMapping
    Page<AccountResponse> findAllAccounts(
            @RequestParam (required = false, defaultValue = "0")int page,
            @RequestParam (required = false, defaultValue = "25") int size
    ) {
        return accountService.findAllAccounts(page, size);
    }
}
