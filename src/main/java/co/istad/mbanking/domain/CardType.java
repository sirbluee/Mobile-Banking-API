package co.istad.mbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "card_typs")
public class CardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private  String name;

    private boolean isDeleted;

    @OneToMany(mappedBy = "cardType")
    private List<Card> cards;

}
