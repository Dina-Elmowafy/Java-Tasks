package EWalletSystem;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

    public static List<Account> accounts = new ArrayList<>();

    static {
        Account admin = new Account("IAM", "IAM123", "01000000000", 30, true);
        accounts.add(admin);
    }

}
