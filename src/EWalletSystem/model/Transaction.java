package EWalletSystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private String fromUser;
    private String toUser;
    private int amount;
    private String timestamp;

    public Transaction(String type, String fromUser, String toUser, int amount) {
        this.type = type;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        switch (type) {
            case "DEPOSIT":
                return String.format("[%s] %s: +%d to %s", timestamp, type, amount, toUser);
            case "WITHDRAW":
                return String.format("[%s] %s: -%d from %s", timestamp, type, amount, fromUser);
            case "TRANSFER":
                return String.format("[%s] %s: %d from %s to %s", timestamp, type, amount, fromUser, toUser);
            default:
                return String.format("[%s] %s: %d", timestamp, type, amount);
        }
    }

    public String getType() { return type; }
    public String getFromUser() { return fromUser; }
    public String getToUser() { return toUser; }
    public int getAmount() { return amount; }
    public String getTimestamp() { return timestamp; }
}