package com.student.ewallet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String walletNumber;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Version
    private long version;

    @OneToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false, unique = true)
    private User owner;

    public Wallet() {}

    public Wallet(String walletNumber, User owner) {
        this.walletNumber = walletNumber;
        this.owner = owner;
    }

    public Long getId() { return id; }
    public String getWalletNumber() { return walletNumber; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public User getOwner() { return owner; }
}
