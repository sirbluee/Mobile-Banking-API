package co.istad.mbanking.features.user.dto;

import co.istad.mbanking.domain.UserAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserDetailsReponse(

        String uuid,
        String name,
        String email,
        String gender,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String village,
        String street,
        String employeeType,
        String position,
        String companyName,
        String nationalCardId,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange,
        String profileImage,
        String oneSignalUserId,
        String studentIdCard,
        boolean isStudent,
        boolean isBlocked,
        boolean isDeleted,
        LocalDate dob,
        LocalDateTime createdAt,
        List<UserAccount> userAccountList,
        List<RoleNameResponse> roles
) {
}
