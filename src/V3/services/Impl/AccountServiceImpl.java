package V3.services.Impl;

import V3.model.Account;
import V3.model.EWalletSystem;
import V3.services.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    EWalletSystem eWalletSystem = new EWalletSystem();

    @Override
    public boolean createAccount(Account account) {
        List<Account> accounts = eWalletSystem.getAccountList();

        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUsername().equals(account.getUsername()))
                .findAny();

        if (optionalAccount.isPresent()) {
            return false;
        } else {
            accounts.add(account);
            return true;
        }
    }

    @Override
    public Account getAccount(Account account) {
        List<Account> accounts = eWalletSystem.getAccountList();

        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUsername().equals(account.getUsername())
                        && acc.getPassword().equals(account.getPassword()))
                .findAny();

        return optionalAccount.orElse(null);
    }

    @Override
    public boolean getPhoneNumber(String phoneNumber) {
        List<Account> accounts = eWalletSystem.getAccountList();
        Optional<Account> optionalAccount = accounts.stream().filter(acc -> acc.getPhoneNumber()
                .equals(phoneNumber)).findAny();
        if (optionalAccount.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
   public boolean getUserName(String userName) {
        List<Account> accounts = eWalletSystem.getAccountList();

        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUsername().equals(userName))
                .findAny();

        if (optionalAccount.isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}