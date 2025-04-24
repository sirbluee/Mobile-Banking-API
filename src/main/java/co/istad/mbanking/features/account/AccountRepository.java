package co.istad.mbanking.features.account;

import co.istad.mbanking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByActNo(String actNo);

    // SELECT EXIST(SELECT * FROM accounts WHERE act_no = ?)
    Boolean existsByActNo(String actNo);

    @Modifying
    @Query("""
    UPDATE Account AS a
    SET a.isHidden = TRUE
    WHERE a.actNo = ?1
""")
    void hideAccountByActNo(String actNo);
}
