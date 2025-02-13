package co.istad.mbanking.features.user;

import co.istad.mbanking.domain.Role;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.user.dto.UserCreateRequest;
import co.istad.mbanking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public void createNew(UserCreateRequest userCreateRequest) {

        // handle existing
        if (userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())){

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Phone number already in use"
            );
        }

        if (userRepository.existsByNationalCardId(userCreateRequest.nationalCardId())){

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Card ID already in use"
            );
        }

        if (userRepository.existsByStudentIdCard(userCreateRequest.studentId())){

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Student ID Already in use"
            );
        }

        if (!userCreateRequest.password()
                .equals(userCreateRequest.ConfirmedPassword())){

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Passwords do not match"
            );
        }

        User user = userMapper.toUser(userCreateRequest);
        user.setCreatedAt(LocalDateTime.now());
        user.setProfileImage("avatar.jpg");
        user.setUuid(UUID.randomUUID().toString());
        user.setBlocked(false);
        user.setDeleted(false);

        // assign default role user
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Role found"));

        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

    }
}
