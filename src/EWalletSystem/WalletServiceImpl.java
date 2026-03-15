package EWalletSystem;

import java.util.Scanner;

public class WalletServiceImpl implements WalletService {

    Scanner sc = new Scanner(System.in);

    @Override
    public void signup() {

        System.out.println("Enter Username:");
        String username = sc.nextLine();

        for(Account a : Wallet.accounts){
            if(a.getUsername().equals(username)){
                System.out.println("Username already exists");
                return;
            }
        }

        System.out.println("Enter Password:");
        String password = sc.nextLine();

        System.out.println("Enter Phone:");
        String phone = sc.nextLine();

        System.out.println("Enter Age:");
        int age = sc.nextInt();
        sc.nextLine();

        if(age < 18){
            System.out.println("Age must be >= 18");
            return;
        }

        Account acc = new Account(username,password,phone,age,false);

        Wallet.accounts.add(acc);

        System.out.println("Account created successfully");
    }

    @Override
    public Account login() {

        Scanner sc = new Scanner(System.in);

        int attempts = 3;

        while(attempts > 0){

            System.out.println("Username:");
            String username = sc.nextLine();

            System.out.println("Password:");
            String password = sc.nextLine();

            for(Account acc : Wallet.accounts){

                if(acc.getUsername().equals(username) && acc.getPassword().equals(password)){

                    if(!acc.isActive()){
                        System.out.println("Account inactive");
                        return null;
                    }

                    System.out.println("Login Success");
                    return acc;
                }
            }

            attempts--;
            System.out.println("Invalid login attempts left: "+attempts);
        }

        return null;
    }

    @Override
    public void deposit(Account acc) {

        System.out.println("Enter amount:");
        double amount = sc.nextDouble();

        if(amount <= 0){
            System.out.println("Invalid amount");
            return;
        }

        acc.setBalance(acc.getBalance()+amount);

        System.out.println("Deposit successful. Balance: "+acc.getBalance());
    }

    @Override
    public void withdraw(Account acc) {

        System.out.println("Enter amount:");
        double amount = sc.nextDouble();

        if(amount <=0){
            System.out.println("Invalid amount");
            return;
        }

        if(acc.getBalance() < amount){
            System.out.println("Not enough balance");
            return;
        }

        acc.setBalance(acc.getBalance()-amount);

        System.out.println("Withdraw successful. Balance: "+acc.getBalance());
    }

    @Override
    public void transfer(Account acc) {

        sc.nextLine();

        System.out.println("Enter receiver username:");
        String user = sc.nextLine();

        if(user.equals(acc.getUsername())){
            System.out.println("Cannot transfer to yourself");
            return;
        }

        Account receiver = null;

        for(Account a : Wallet.accounts){
            if(a.getUsername().equals(user)){
                receiver = a;
            }
        }

        if(receiver == null){
            System.out.println("User not found");
            return;
        }

        System.out.println("Enter amount:");
        double amount = sc.nextDouble();

        if(amount <=0){
            System.out.println("Invalid amount");
            return;
        }

        if(acc.getBalance() < amount){
            System.out.println("Not enough balance");
            return;
        }

        acc.setBalance(acc.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        System.out.println("Transfer successful");
    }

    @Override
    public void showDetails(Account acc) {

        System.out.println("Username: "+acc.getUsername());
        System.out.println("Phone: "+acc.getPhone());
        System.out.println("Age: "+acc.getAge());
        System.out.println("Balance: "+acc.getBalance());
    }

    @Override
    public void changePassword(Account acc) {

        sc.nextLine();

        System.out.println("Old Password:");
        String old = sc.nextLine();

        if(!acc.getPassword().equals(old)){
            System.out.println("Wrong password");
            return;
        }

        System.out.println("New Password:");
        String newPass = sc.nextLine();

        if(newPass.equals(old)){
            System.out.println("Cannot use same password");
            return;
        }

        acc.setPassword(newPass);

        System.out.println("Password changed successfully");
    }
}
