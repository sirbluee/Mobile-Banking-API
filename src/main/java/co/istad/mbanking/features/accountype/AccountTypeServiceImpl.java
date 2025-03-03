package co.istad.mbanking.features.accountype;

import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.features.accountype.dto.AccountTypeResponse;
import co.istad.mbanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    private final AccountTypeMapper accountTypeMapper;

    @Override
    public List<AccountTypeResponse> findList() {

        List<AccountType> accountTypes = accountTypeRepository.findAll();

        return accountTypeMapper.toAccountTypeResponsesList(accountTypes);
    }

    @Override
    public AccountTypeResponse findByAlias(String alias) {

        AccountType accountType = accountTypeRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }
}
