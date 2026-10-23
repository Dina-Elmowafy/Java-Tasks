package EWalletSystem.services;

import EWalletSystem.exception.*;
import EWalletSystem.model.Account;

public interface AccountService {
    boolean createAccount(Account account);
    Account login(String username, String password);
    boolean isPhoneNumberUnique(String phoneNumber);
    boolean isUsernameUnique(String username);
    Account getAccountByUsername(String username);
    Account deposit(Account account, int amount) throws MinimumAmountException, AccountNotFoundException;
    Account withdraw(Account account, int amount) throws AccountNotFoundException, InsufficientBalanceException;
    Account transfer(Account fromAccount, String toUsername, int amount)
            throws AccountNotFoundException, InsufficientBalanceException, IllegalArgumentException;
}