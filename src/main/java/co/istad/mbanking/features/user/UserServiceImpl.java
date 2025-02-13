package co.istad.mbanking.features.user;

import co.istad.mbanking.features.user.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public void createNew(UserCreateRequest userCreateRequest) {

    }
}
