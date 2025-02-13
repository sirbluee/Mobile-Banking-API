package co.istad.mbanking.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private Integer pin;  // Store 4-digital

    @Column(nullable = false, length = 50)
    private String name;
    private String password;
    private String email;

    @Column(length = 8)
    private String gender;

    @Column(unique = true, nullable = false, length = 30)
    private String phoneNumber;

    @Column(length = 100)
    private String cityOrProvince;
    @Column(length = 100)
    private String KhanOrDistrict;
    @Column(length = 100)
    private String sangkatOrCommune;
    @Column(length = 100)
    private String village;
    @Column(length = 100)
    private String street;
    @Column(length = 100)
    private String employeeType;
    @Column(length = 100)
    private String position;
    @Column(length = 100)
    private String companyName;

    @Column(length = 50, unique = true, nullable = false)
    private String nationalCardId;

    @Column(length = 100)
    private String mainSourceOfIncome;

    private BigDecimal monthlyIncomeRange;

    private String profileImage;

    private String oneSignalUserId;

    private String studentIdCard;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccountList;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    private LocalDateTime createdAt;

    private boolean isStudent;
    private boolean isDeleted;
    private boolean isBlocked;

}
