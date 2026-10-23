package EWalletSystem.service.impl;



import EWalletSystem.model.*;
import EWalletSystem.repository.WalletRepository;
import EWalletSystem.service.WalletService;
import EWalletSystem.util.TransactionLogger;

import java.util.Scanner;

public class WalletServiceImpl implements WalletService {

    private Scanner scanner = new Scanner(System.in);

    private double readAmount() {
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return -1;
        }
        return amount;
    }

    @Override
    public void signup() {

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        if (!BaseAccount.validateUsername(username) || WalletRepository.usernameExists(username)) {
            System.out.println("Invalid username.");
            return;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (!BaseAccount.validatePassword(password)) {
            System.out.println("Weak password.");
            return;
        }

        System.out.println("Enter phone:");
        String phone = scanner.nextLine();

        if (!BaseAccount.validatePhone(phone) || WalletRepository.phoneExists(phone)) {
            System.out.println("Invalid phone.");
            return;
        }

        System.out.println("Enter age:");
        int age = scanner.nextInt();
        scanner.nextLine();

        if (!BaseAccount.validateAge(age)) {
            System.out.println("Age must be 18+.");
            return;
        }

        BaseAccount acc = new UserAccount(username, password, phone, age);
        WalletRepository.save(acc);

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

            BaseAccount acc = WalletRepository.findByUsername(username);

            if (acc != null && acc.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return acc;
            }

            attempts--;
            System.out.println("Invalid credentials. Attempts left: " + attempts);
        }
        return null;
    }

    @Override
    public void deposit(BaseAccount acc) {
        System.out.println("Enter amount:");
        double amount = readAmount();
        if (amount <= 0) return;

        acc.setBalance(acc.getBalance() + amount);

        new TransactionLogger("Deposit: " + amount).start();
    }

    @Override
    public void withdraw(BaseAccount acc) {
        System.out.println("Enter amount:");
        double amount = readAmount();
        if (amount <= 0 || acc.getBalance() < amount) return;

        acc.setBalance(acc.getBalance() - amount);

        new TransactionLogger("Withdraw: " + amount).start();
    }

    @Override
    public void transfer(BaseAccount sender) {

        System.out.println("Receiver:");
        String name = scanner.nextLine();

        BaseAccount receiver = WalletRepository.findByUsername(name);

        if (receiver == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Amount:");
        double amount = readAmount();

        if (amount <= 0 || sender.getBalance() < amount) return;

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        new TransactionLogger("Transfer: " + amount).start();
    }

    @Override
    public void showDetails(BaseAccount acc) {
        System.out.println(acc.getUsername());
        System.out.println(acc.getBalance());
    }

    @Override
    public void changePassword(BaseAccount acc) {
        System.out.println("New password:");
        String pass = scanner.nextLine();

        if (!BaseAccount.validatePassword(pass)) {
            System.out.println("Weak password.");
            return;
        }

        acc.setPassword(pass);
    }
}
