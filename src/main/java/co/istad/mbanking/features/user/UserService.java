package co.istad.mbanking.features.user;

import co.istad.mbanking.features.user.dto.UserCreateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService extends JpaRepository {

    void createNew(UserCreateRequest userCreateRequest);
}
