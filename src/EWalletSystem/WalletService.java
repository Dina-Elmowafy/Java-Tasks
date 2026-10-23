package EWalletSystem;

public interface WalletService {

    void signup();

    Account login();

    void deposit(Account acc);

    void withdraw(Account acc);

    void transfer(Account acc);

    void showDetails(Account acc);

    void changePassword(Account acc);

}
