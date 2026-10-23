package V1.service;

import V1.model.Account;

public interface AccountService {
     boolean CreateAccount(Account account);   //signup
    boolean getAccountByUseUserNameAndPassword(Account account);
}
