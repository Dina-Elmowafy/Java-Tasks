package EWalletSystem;

public interface WalletService {

    void signup();

<<<<<<< HEAD
    Account login();

    void deposit(Account acc);

    void withdraw(Account acc);

    void transfer(Account acc);

    void showDetails(Account acc);

    void changePassword(Account acc);

=======
    BaseAccount login();

    void deposit(BaseAccount account);

    void withdraw(BaseAccount account);

    void transfer(BaseAccount sender);

    void showDetails(BaseAccount account);

    void changePassword(BaseAccount account);
>>>>>>> 73597da457f5ad08959ffb3f416e398fbd381184
}
