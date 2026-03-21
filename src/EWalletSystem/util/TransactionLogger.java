package EWalletSystem.util;


// Thread class for logging transactions
public class TransactionLogger extends Thread {

    private String message;

    public TransactionLogger(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("LOG: " + message);
    }
}