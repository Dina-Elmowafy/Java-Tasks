package service;

import model.Account;

public interface AccountService {

    boolean signup(Account account);

    Account login(String username, String password);

    boolean updatePassword(String email, String newPassword);

    boolean deleteAccount(long accountId);
}