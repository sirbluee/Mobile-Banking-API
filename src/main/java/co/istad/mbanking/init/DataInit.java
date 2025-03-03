package co.istad.mbanking.init;

import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.domain.Role;
import co.istad.mbanking.features.accountype.AccountTypeRepository;
import co.istad.mbanking.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

// to create bean
@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;

    private final AccountTypeRepository accountTypeRepository;

    // this method run when run started project
    @PostConstruct
    void initRole(){

        // auto generate roles
        if (roleRepository.count() == 0) {
            Role user = new Role();
            user.setName("USER");

            Role staff = new Role();
            staff.setName("STAFF");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(
                    List.of(user, staff, customer, admin)
            );
        }
    }

    @PostConstruct
    void initAccountType(){

        if (accountTypeRepository.count() == 0) {
            AccountType savingActType = new AccountType();
            savingActType.setName("Saving Account");
            savingActType.setAlias("saving-account");
            savingActType.setDescription("A savings account is a bank or credit union account designed to keep your money safe while providing interest. Learn how savings accounts work.");

            accountTypeRepository.save(savingActType);

            AccountType payRollActType = new AccountType();
            payRollActType.setName("Payroll Account");
            payRollActType.setAlias("payroll-account");
            payRollActType.setDescription("Payroll account is a dedicated bank account used by employers to deposit employees' salaries, making it easy to manage and track payroll transactions.");

            accountTypeRepository.save(payRollActType);

            AccountType cardActType = new AccountType();
            cardActType.setName("Card Account");
            cardActType.setAlias("card-account");
            cardActType.setDescription("Card Account means the account opened by the Bank for issuance, billing and maintenance of Primary Card(s) and Supplementary Card(s).");

            accountTypeRepository.save(cardActType);
        }
    }
}
