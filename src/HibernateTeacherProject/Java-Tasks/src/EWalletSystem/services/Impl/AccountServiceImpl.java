package EWalletSystem.services.Impl;

import EWalletSystem.exception.*;
import EWalletSystem.model.Account;
import EWalletSystem.model.EWalletSystem;
import EWalletSystem.model.Transaction;
import EWalletSystem.services.AccountService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final EWalletSystem eWalletSystem;

    public AccountServiceImpl() {
        this.eWalletSystem = EWalletSystem.getInstance();
    }

    // ========== Helper methods ==========
    private Optional<Account> findAccountByUsername(String username) {
        return eWalletSystem.getAccountList().stream()
                .filter(acc -> acc.getUsername().equals(username))
                .findAny();
    }

    private Optional<Account> findAccountByUsernameAndPassword(String username, String password) {
        return eWalletSystem.getAccountList().stream()
                .filter(acc -> acc.getUsername().equals(username) && acc.getPassword().equals(password))
                .findAny();
    }

    private void updateBalance(Account account, int delta) {
        account.setBalance(account.getBalance() + delta);
    }

    private void recordTransaction(String type, String fromUser, String toUser, int amount) {
        Transaction tx = new Transaction(type, fromUser, toUser, amount);
        eWalletSystem.getTransactionHistory().addTransaction(tx);
    }

    // ========== Public API ==========
    @Override
    public boolean createAccount(Account account) {
        if (!isUsernameUnique(account.getUsername())) {
            return false;
        }
        eWalletSystem.getAccountList().add(account);
        return true;
    }

    @Override
    public Account login(String username, String password) {
        return findAccountByUsernameAndPassword(username, password).orElse(null);
    }

    @Override
    public boolean isPhoneNumberUnique(String phoneNumber) {
        if (phoneNumber == null) return true;
        return eWalletSystem.getAccountList().stream()
                .noneMatch(acc -> phoneNumber.equals(acc.getPhoneNumber()));
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return findAccountByUsername(username).isEmpty();
    }

    @Override
    public Account getAccountByUsername(String username) {
        return findAccountByUsername(username).orElse(null);
    }

    @Override
    public Account deposit(Account account, int amount) throws MinimumAmountException, AccountNotFoundException {
        if (amount < 100) {
            throw new MinimumAmountException("Minimum deposit amount is 100");
        }
        Account existing = findAccountByUsername(account.getUsername())
                .orElseThrow(() -> new AccountNotFoundException("Account not found for deposit"));
        updateBalance(existing, amount);
        recordTransaction("DEPOSIT", null, existing.getUsername(), amount);
        return existing;
    }

    @Override
    public Account withdraw(Account account, int amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account existing = findAccountByUsername(account.getUsername())
                .orElseThrow(() -> new AccountNotFoundException("Account not found for withdrawal"));
        if (amount > existing.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        updateBalance(existing, -amount);
        recordTransaction("WITHDRAW", existing.getUsername(), null, amount);
        return existing;
    }

    @Override
    public Account transfer(Account fromAccount, String toUsername, int amount)
            throws AccountNotFoundException, InsufficientBalanceException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (fromAccount.getUsername().equals(toUsername)) {
            throw new IllegalArgumentException("Cannot transfer to yourself");
        }
        Account from = findAccountByUsername(fromAccount.getUsername())
                .orElseThrow(() -> new AccountNotFoundException("Source account not found"));
        Account to = findAccountByUsername(toUsername)
                .orElseThrow(() -> new AccountNotFoundException("Target account not found"));
        if (amount > from.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance for transfer");
        }
        updateBalance(from, -amount);
        updateBalance(to, amount);
        recordTransaction("TRANSFER", from.getUsername(), to.getUsername(), amount);
        return from;
    }
}