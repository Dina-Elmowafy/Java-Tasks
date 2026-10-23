package EWalletSystem.repository;

import EWalletSystem.model.AdminAccount;
import EWalletSystem.model.BaseAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WalletRepository {

    public static List<BaseAccount> accounts = new ArrayList<>();

    static {
        accounts.add(new AdminAccount("IAM", "IAM123A", "01000000000", 30));
    }

    public static BaseAccount findByUsername(String username) {
        Optional<BaseAccount> account = accounts.stream()
                .filter(acc -> acc.getUsername().equals(username))
                .findFirst();
        return account.orElse(null);
    }

    public static boolean usernameExists(String username) {
        return accounts.stream().anyMatch(acc -> acc.getUsername().equals(username));
    }

    public static boolean phoneExists(String phone) {
        return accounts.stream().anyMatch(acc -> acc.getPhone().equals(phone));
    }

    public static void save(BaseAccount account) {
        accounts.add(account);
    }
}