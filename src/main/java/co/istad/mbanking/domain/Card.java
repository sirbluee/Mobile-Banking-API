package co.istad.mbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 16)
    private String number;

    @Column(nullable = false, length = 3)
    private String cvv;

    private String holder;
    private LocalDate issuedAt;
    private LocalDate expiredAt;

    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CardType cardType;

    @OneToOne(mappedBy = "card")
    private Account account;
}
