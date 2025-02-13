package co.istad.mbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Account transferReceiver; // use when transaction type is Transfer

    private String paymentReceiver;

    private BigDecimal amount;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = false, length = 50)
    private String transactionType; // transfer and payment

    private boolean status; // pending or completed or failed

    private LocalDateTime transactionAt;

}
