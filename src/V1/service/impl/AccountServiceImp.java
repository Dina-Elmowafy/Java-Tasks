package V1.service.impl;

import V1.model.Account;
import V1.model.EwalletSystem;
import V1.service.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImp implements AccountService{

    private EwalletSystem ewalletSystem =new EwalletSystem();

    @Override
    public boolean CreateAccount(Account account) {
        List<Account> accounts =ewalletSystem.getAccounts();
       Optional<Account> optionalAccount= accounts.stream().filter(acc->acc.getUserName().equals(account.getUserName())).findAny();
       if(optionalAccount.isPresent())
       {
           return false;
       }
        ewalletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean getAccountByUseUserNameAndPassword(Account account) {
        return false;
    }
}
