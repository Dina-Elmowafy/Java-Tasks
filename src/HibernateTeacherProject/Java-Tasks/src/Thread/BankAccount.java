package Thread;

public class BankAccount {
   private double balance=0.0;

    public synchronized void  deposit(double amount){
        balance+=amount;
        System.out.println("Deposited: " + amount + ", Balance = " + balance);
        notify();
    }
    public synchronized void  withDrow(double amount) {
    while (balance<amount)
    {
        System.out.println("Not enough balance. Waiting...");

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
        balance -= amount;
        System.out.println("Withdrawn: " + amount + ", Balance = " + balance);
    }
}
