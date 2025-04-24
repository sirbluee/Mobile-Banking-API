package co.istad.mbanking.features.account;

import co.istad.mbanking.domain.Account;
import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.domain.UserAccount;
import co.istad.mbanking.features.account.dto.AccountCreateRequest;
import co.istad.mbanking.features.account.dto.AccountRenameRequest;
import co.istad.mbanking.features.accountype.AccountTypeRepository;
import co.istad.mbanking.features.user.UserRepository;
import co.istad.mbanking.mapper.AccountMapper;
import co.istad.mbanking.features.account.dto.AccountResponse;
import co.istad.mbanking.util.RandomUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final UserAccountRepository  userAccountRepository;
    private final AccountMapper accountMapper;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;
    private  final UserRepository userRepository;

    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {
        // check account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type Not Found"));

        // user by uuid
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        // map account dto to account entity
        Account account = accountMapper.fromAccountCreateRequestToAccount(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setActNo(RandomUtil.generateRandom9Digits());
        account.setHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setCreatedAt(LocalDateTime.now());
        userAccount.setDeleted(false);
        userAccount.setBlocked(false);

        userAccountRepository.save(userAccount);
    }
    // find account by actNo
    @Override
    public AccountResponse findByActNo(String actNo) {

        Account account = accountRepository
                .findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account doesn't exist"
                ));
        log.info("Account found with act no {}", actNo);
        return accountMapper.toAccountResponse(account);
    }

    // rename account by actNo
    @Override
    public AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest) {

        // check actNo is exit
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not Found"));

        // check old and alias
        if (account.getActName().equals(accountRenameRequest.newName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "New Name must not be the same old Name");
        }
        account.setActName(accountRenameRequest.newName());
        account =  accountRepository.save(account);
        log.info("Account renamed with name {}", accountRenameRequest.newName());
        return accountMapper.toAccountResponse(account);
    }

    @Override
    @Transactional
    public void hideAccount(String actNo) {
        if (!accountRepository.existsByActNo(actNo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not Found");
        }
        try {
            // FIXED: No need to call through interface
            accountRepository.hideAccountByActNo(actNo);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong while hiding account"
            );
        }
        log.info("Account hidden with name {}", actNo);
    }

    // list all account
    @Override
    public Page<AccountResponse> findAllAccounts(int page, int size) {
        //validate
        if (page < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page Number Must Be Positive");
        }
        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than zero");
        }

        Sort sortByActName = Sort.by(Sort.Direction.ASC, "ActName");
        PageRequest pageRequest = PageRequest.of(page, size, sortByActName);

        Page<Account>  accounts= accountRepository.findAll(pageRequest);
        return accounts.map(accountMapper::toAccountResponse);
    }
}
