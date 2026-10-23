package EWalletSystem.model;

import java.util.ArrayList;
import java.util.List;

public class EWalletSystem {
    private static EWalletSystem instance;
    private final String systemName = "V3_EWallet_System";
    private List<Account> accountList;
    private TransactionHistory transactionHistory;

    private EWalletSystem() {
        accountList = new ArrayList<>();
        transactionHistory = new TransactionHistory();
    }

    public static synchronized EWalletSystem getInstance() {
        if (instance == null) {
            instance = new EWalletSystem();
        }
        return instance;
    }

    public String getSystemName() { return systemName; }
    public List<Account> getAccountList() { return accountList; }
    public TransactionHistory getTransactionHistory() { return transactionHistory; }
}