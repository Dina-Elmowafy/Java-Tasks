package com.student.ewallet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_transactions")
public class WalletTransaction {
    @Id
    @SequenceGenerator(
        name = "transaction_seq_generator",
        sequenceName = "wallet_transaction_seq",
        allocationSize = 1,
        initialValue = 1000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "transaction_seq_generator"
    )
    private Long id;

    @Column(nullable = false, length = 20)
    private String walletNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    private String otherWalletNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public WalletTransaction() {}

    public WalletTransaction(String walletNumber, TransactionType type,
                             BigDecimal amount, String otherWalletNumber) {
        this.walletNumber = walletNumber;
        this.type = type;
        this.amount = amount;
        this.otherWalletNumber = otherWalletNumber;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getWalletNumber() { return walletNumber; }
    public TransactionType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public String getOtherWalletNumber() { return otherWalletNumber; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
