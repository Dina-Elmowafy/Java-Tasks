package Thread;

public class BankApp {
    public static void main(String[] args) {
    BankAccount bankAccount = new BankAccount();
    Thread depositor = new Thread(()-> {while(true){double amount =100.00;
            bankAccount.deposit(amount);
            try {
                Thread.sleep(1500); // كل 1.5 ثانية
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }});
    Thread withDraw = new Thread(()->{while (true) {
        double amount = 200.00;
        bankAccount.withDrow(amount);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    });
        depositor.start();
        withDraw.start();
    }
}
