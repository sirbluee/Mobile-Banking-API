package co.istad.mbanking.features.user;

import co.istad.mbanking.base.BasedMessage;
import co.istad.mbanking.domain.Role;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.user.dto.UserCreateRequest;
import co.istad.mbanking.features.user.dto.UserDetailsReponse;
import co.istad.mbanking.features.user.dto.UserResponse;
import co.istad.mbanking.features.user.dto.UserUpdateRequest;
import co.istad.mbanking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;


    //    private final UserService userService;
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

        if (userRepository.existsByStudentIdCard(userCreateRequest.studentIdCard())){

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Student ID Already in use"
            );
        }

        if (!userCreateRequest.password()
                .equals(userCreateRequest.confirmedPassword())){

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

        // create dynamic role client
        userCreateRequest.roles().forEach(r -> {
           Role newRole = roleRepository.findByName(r.name())
                           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Role found"));

           roles.add(newRole);
        });

        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

    }

    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest) {

        // check uuid existing
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No User found"));

        log.info("Before User: {}", user.getName());

        userMapper.fromUserUpdateRequest(userUpdateRequest, user);

        log.info("After User: {}", user.getName());

        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    // find user by uuid
    @Override
    public UserDetailsReponse findByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No User found"));

        return userMapper.toUserDetailsReponse(user);
    }

    // do transaction on block user
    @Transactional
    @Override
    public BasedMessage blockByUuid(String uuid) {

        if (userRepository.existsByUuid(uuid)){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }

        userRepository.blockByUuid(uuid);

        return new BasedMessage("User has been blocked!");
    }

    // delete user by uuid
    @Override
    public void deleteByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No User found"));

        userRepository.delete(user);

    }

    // get all users with pagination
    @Override
    public Page<UserResponse> findList(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit);

        Page<User> users = userRepository.findAll(pageRequest);

        return users.map(userMapper::toUserResponse);
    }

}
