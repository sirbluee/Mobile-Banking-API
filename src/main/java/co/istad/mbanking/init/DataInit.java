package co.istad.mbanking.init;

import co.istad.mbanking.domain.Role;
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

    // this method run when run started project
    @PostConstruct
    void Init(){

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
}
