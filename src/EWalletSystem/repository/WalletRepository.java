package EWalletSystem.repository;


import EWalletSystem.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WalletRepository {

    private static List<BaseAccount> accounts = new ArrayList<>();

    static {
        accounts.add(new AdminAccount("IAM", "IAM123A", "01000000000", 30));
    }

    public static List<BaseAccount> getAllAccounts() {
        return accounts;
    }

    public static BaseAccount findByUsername(String username) {
        Optional<BaseAccount> acc = accounts.stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst();
        return acc.orElse(null);
    }

    public static boolean usernameExists(String username) {
        return accounts.stream().anyMatch(a -> a.getUsername().equals(username));
    }

    public static boolean phoneExists(String phone) {
        return accounts.stream().anyMatch(a -> a.getPhone().equals(phone));
    }

    public static void save(BaseAccount account) {
        accounts.add(account);
    }
}
