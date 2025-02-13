package co.istad.mbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 9)
    private String actNumber;

    @Column(nullable = false, length = 50, unique = true)
    private  String actName;

    @Column(nullable = false)
    private String alias;

    @Column(nullable = false)
    private BigDecimal balance;

    private BigDecimal transferLimit;

    // account has type
    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<UserAccount> userAccountList;

    @OneToOne
    private Card card;

    private boolean isHidden; // use to hidden account

}
