package V3.services;

import V3.model.Account;

public interface AccountService {
     boolean createAccount(Account account);
    Account getAccount(Account account);
    boolean getPhoneNumber(String phoneNumber);
    boolean getUserName(String userName);
}