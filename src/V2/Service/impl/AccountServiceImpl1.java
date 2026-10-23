package V2.Service.impl;

import V2.Service.AccountService1;
import V2.model.Account;
import V2.model.EwalletSystem;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl1 implements AccountService1 {
    // قائمة الحسابات مشتركة لكل النسخ
    private static EwalletSystem ewalletSystem = new EwalletSystem();

    @Override
    public boolean creatAccount(Account account) {
        List<Account> accounts = ewalletSystem.getAccounts();

        // تحقق من وجود الـ username مسبقًا باستخدام Optional
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()))
                .findAny();

        if (optionalAccount.isPresent()) {
            return false; // الـ username موجود بالفعل
        }

        accounts.add(account); // إضافة الحساب الجديد
        return true;
    }

    @Override
    public boolean getAccount(Account account) {
        List<Account> accounts = ewalletSystem.getAccounts();

        // تحقق من وجود الحساب بالـ username والـ password باستخدام Optional
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName())
                        && acc.getPassword().equals(account.getPassword()))
                .findAny();

        return optionalAccount.isPresent();
    }
}