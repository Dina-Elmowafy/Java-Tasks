package EWalletSystem;

// Thread used for logging transactions in background
public class TransactionLogger extends Thread {

    private String message;

    public TransactionLogger(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Logger interrupted");
        }

        System.out.println("Transaction Log: " + message);
    }
}
