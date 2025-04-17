package co.istad.mbanking.features.account.dto;

import co.istad.mbanking.features.accountype.dto.AccountTypeResponse;
import co.istad.mbanking.features.user.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse (
        String actNo,
        String actName,
        String alias,
        BigDecimal balance,
        BigDecimal transferLimit,
        AccountTypeResponse accountType,

        UserResponse user
) {
}
