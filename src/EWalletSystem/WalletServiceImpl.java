package EWalletSystem;

import java.util.Scanner;

public class WalletServiceImpl implements WalletService {

    private Scanner scanner = new Scanner(System.in);

    private double readAmount() {
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return -1;
        }
        return amount;
    }

    @Override
    public void signup() {

        System.out.println("Enter username (start with capital, letters only):");
        String username = scanner.nextLine();
        if (!BaseAccount.validateUsername(username) || WalletRepository.usernameExists(username)) {
            System.out.println("Invalid or existing username.");
            return;
        }

        System.out.println("Enter password (min6 chars, 1 upper, 1 lower, 1 digit):");
        String password = scanner.nextLine();
        if (!BaseAccount.validatePassword(password)) {
            System.out.println("Password too weak.");
            return;
        }

        System.out.println("Enter phone (Egyptian format):");
        String phone = scanner.nextLine();
        if (!BaseAccount.validatePhone(phone) || WalletRepository.phoneExists(phone)) {
            System.out.println("Invalid or existing phone.");
            return;
        }

        System.out.println("Enter age (>=18):");
        int age = scanner.nextInt();
        scanner.nextLine();
        if (!BaseAccount.validateAge(age)) {
            System.out.println("You must be at least 18.");
            return;
        }

        BaseAccount account = new UserAccount(username, password, phone, age);
        WalletRepository.save(account);

        System.out.println("Account created successfully!");
    }

    @Override
    public BaseAccount login() {

        int attempts = 3;
        while (attempts > 0) {
            System.out.println("Username:");
            String username = scanner.nextLine();
            System.out.println("Password:");
            String password = scanner.nextLine();

            BaseAccount account = WalletRepository.findByUsername(username);

            if (account != null && account.getPassword().equals(password)) {
                if (!account.isActive()) {
                    System.out.println("Account inactive.");
                    return null;
                }
                System.out.println("Login successful!");
                return account;
            }

            attempts--;
            System.out.println("Invalid credentials. Attempts left: " + attempts);
        }
        return null;
    }

    @Override
    public void deposit(BaseAccount account) {
        System.out.println("Enter deposit amount:");
        double amount = readAmount();
        if (amount <= 0) return;
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful. Balance: " + account.getBalance());
        new TransactionLogger("Deposit " + amount + " to " + account.getUsername()).start();
    }

    @Override
    public void withdraw(BaseAccount account) {
        System.out.println("Enter withdraw amount:");
        double amount = readAmount();
        if (amount <= 0) return;
        if (account.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdraw successful. Balance: " + account.getBalance());
        new TransactionLogger("Withdraw " + amount + " from " + account.getUsername()).start();
    }

    @Override
    public void transfer(BaseAccount sender) {
        scanner.nextLine();
        System.out.println("Receiver username:");
        String receiverName = scanner.nextLine();
        if (receiverName.equals(sender.getUsername())) {
            System.out.println("Cannot transfer to yourself.");
            return;
        }
        BaseAccount receiver = WalletRepository.findByUsername(receiverName);
        if (receiver == null) {
            System.out.println("Receiver not found.");
            return;
        }
        System.out.println("Transfer amount:");
        double amount = readAmount();
        if (amount <= 0) return;
        if (sender.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        System.out.println("Transfer successful.");
        new TransactionLogger("Transfer " + amount + " from " + sender.getUsername() + " to " + receiver.getUsername()).start();
    }

    @Override
    public void showDetails(BaseAccount account) {
        System.out.println("Username: " + account.getUsername());
        System.out.println("Phone: " + account.getPhone());
        System.out.println("Age: " + account.getAge());
        System.out.println("Balance: " + account.getBalance());
    }

    @Override
    public void changePassword(BaseAccount account) {
        scanner.nextLine();
        System.out.println("Old password:");
        String oldPass = scanner.nextLine();
        if (!account.getPassword().equals(oldPass)) {
            System.out.println("Incorrect password.");
            return;
        }
        System.out.println("New password:");
        String newPass = scanner.nextLine();
        if (!BaseAccount.validatePassword(newPass) || newPass.equals(oldPass)) {
            System.out.println("Invalid or same as old password.");
            return;
        }
        account.setPassword(newPass);
        System.out.println("Password changed successfully!");
    }
}