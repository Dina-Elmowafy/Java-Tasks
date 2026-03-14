package EWalletSystem;

public interface WalletService {

    void signup();

    BaseAccount login();

    void deposit(BaseAccount account);

    void withdraw(BaseAccount account);

    void transfer(BaseAccount sender);

    void showDetails(BaseAccount account);

    void changePassword(BaseAccount account);
}
