package EWalletSystem.service;

import EWalletSystem.model.BaseAccount;

public interface WalletService {

    void signup();
    BaseAccount login();
    void deposit(BaseAccount account);
    void withdraw(BaseAccount account);
    void transfer(BaseAccount account);
    void showDetails(BaseAccount account);
    void changePassword(BaseAccount account);
}