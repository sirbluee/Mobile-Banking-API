package co.istad.mbanking.features.accountype;

import co.istad.mbanking.features.accountype.dto.AccountTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account-types")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @GetMapping()
    List<AccountTypeResponse> findList() {

        return accountTypeService.findList();
    }

    @GetMapping("/{alias}")
    AccountTypeResponse findByAlias(@PathVariable String alias) {

        return accountTypeService.findByAlias(alias);
    }

}
