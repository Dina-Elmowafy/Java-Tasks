package com.student.ewallet.service;

import com.student.ewallet.model.*;
import com.student.ewallet.util.JpaUtil;
import com.student.ewallet.util.PasswordUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class WalletService {
    public User register(String name, String email, String password) {
        if (name.isBlank() || email.isBlank() || password.length() < 6)
            throw new IllegalArgumentException("Enter valid data; password needs 6 characters.");

        return inTransaction(em -> {
            Long count = em.createQuery(
                    "select count(u) from User u where lower(u.email) = :email", Long.class)
                    .setParameter("email", email.toLowerCase()).getSingleResult();
            if (count > 0) throw new IllegalArgumentException("Email already exists.");

            User user = new User(name.trim(), email.trim().toLowerCase(),
                    PasswordUtil.hash(password));
            Wallet wallet = new Wallet(newWalletNumber(), user);
            user.setWallet(wallet);
            em.persist(user);
            return user;
        });
    }

    public User login(String email, String password) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            List<User> users = em.createQuery(
                    "select u from User u where lower(u.email) = :email", User.class)
                    .setParameter("email", email.trim().toLowerCase()).getResultList();
            if (users.size() == 1 && PasswordUtil.matches(password, users.get(0).getPasswordHash()))
                return users.get(0);
            throw new IllegalArgumentException("Wrong email or password.");
        } finally {
            em.close();
        }
    }

    public Wallet getWallet(Long userId) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return em.createQuery(
                    "select w from Wallet w join fetch w.owner where w.owner.id = :id", Wallet.class)
                    .setParameter("id", userId).getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<WalletTransaction> getTransactions(String walletNumber) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return em.createQuery(
                    "from WalletTransaction t where t.walletNumber = :number order by t.createdAt desc",
                    WalletTransaction.class)
                    .setParameter("number", walletNumber).setMaxResults(50).getResultList();
        } finally {
            em.close();
        }
    }

    public void deposit(Long userId, BigDecimal amount) {
        validateAmount(amount);
        inTransaction(em -> {
            Wallet wallet = findByUser(em, userId);
            wallet.setBalance(wallet.getBalance().add(amount));
            em.persist(new WalletTransaction(wallet.getWalletNumber(),
                    TransactionType.DEPOSIT, amount, null));
            return null;
        });
    }

    public void withdraw(Long userId, BigDecimal amount) {
        validateAmount(amount);
        inTransaction(em -> {
            Wallet wallet = findByUser(em, userId);
            if (wallet.getBalance().compareTo(amount) < 0)
                throw new IllegalArgumentException("Balance is not enough.");
            wallet.setBalance(wallet.getBalance().subtract(amount));
            em.persist(new WalletTransaction(wallet.getWalletNumber(),
                    TransactionType.WITHDRAW, amount, null));
            return null;
        });
    }

    public void transfer(Long userId, String receiverNumber, BigDecimal amount) {
        validateAmount(amount);
        inTransaction(em -> {
            Wallet sender = findByUser(em, userId);
            if (sender.getWalletNumber().equals(receiverNumber))
                throw new IllegalArgumentException("You cannot transfer to your wallet.");
            Wallet receiver = em.createQuery(
                    "from Wallet w where w.walletNumber = :number", Wallet.class)
                    .setParameter("number", receiverNumber.trim()).getResultStream()
                    .findFirst().orElseThrow(() ->
                            new IllegalArgumentException("Receiver wallet was not found."));
            if (sender.getBalance().compareTo(amount) < 0)
                throw new IllegalArgumentException("Balance is not enough.");

            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
            em.persist(new WalletTransaction(sender.getWalletNumber(),
                    TransactionType.TRANSFER_OUT, amount, receiver.getWalletNumber()));
            em.persist(new WalletTransaction(receiver.getWalletNumber(),
                    TransactionType.TRANSFER_IN, amount, sender.getWalletNumber()));
            return null;
        });
    }

    private Wallet findByUser(EntityManager em, Long userId) {
        return em.createQuery("from Wallet w where w.owner.id = :id", Wallet.class)
                .setParameter("id", userId).getSingleResult();
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero.");
        if (amount.scale() > 2)
            throw new IllegalArgumentException("Use at most 2 decimal places.");
    }

    private String newWalletNumber() {
        return "W" + UUID.randomUUID().toString().replace("-", "")
                .substring(0, 11).toUpperCase();
    }

    private <T> T inTransaction(Work<T> work) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            T result = work.run(em);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @FunctionalInterface
    private interface Work<T> { T run(EntityManager em); }
}
