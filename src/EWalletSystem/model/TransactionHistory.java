package EWalletSystem.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getFormattedHistory() {
        if (transactions.isEmpty()) {
            return "No transactions yet.";
        }
        StringBuilder sb = new StringBuilder("\n===== Transaction History =====\n");
        for (Transaction t : transactions) {
            sb.append(t).append("\n");
        }
        return sb.toString();
    }
}