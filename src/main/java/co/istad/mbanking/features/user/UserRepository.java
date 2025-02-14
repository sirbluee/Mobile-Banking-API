package co.istad.mbanking.features.user;

import co.istad.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNationalCardId(String nationalCardId);

    boolean existsByStudentIdCard(String studentIdCard);

    @Query("SELECT u FROM User AS u WHERE u.uuid = :uuid")
    Optional<User>findByUuid(String uuid);

    boolean existsByUuid(String uuid);


    // Modify used to handle JPQL can do a transaction
    @Modifying
    @Query("UPDATE User AS u SET u.isBlocked = TRUE WHERE u.uuid = ?1")
    void blockByUuid(String uuid);

}
