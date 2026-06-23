package EWalletSystem.services.Impl;

import EWalletSystem.exception.*;
import EWalletSystem.model.Account;
import EWalletSystem.model.EWalletSystem;
import EWalletSystem.services.AccountService;
import EWalletSystem.services.ApplicationService;
import EWalletSystem.validator.*;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {
    private Account currentAccount;
    private final AccountService accountService;
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;
    private final PhoneValidator phoneValidator;
    private final AgeValidator ageValidator;
    private final Scanner scanner;

    public ApplicationServiceImpl() {
        this.accountService = new AccountServiceImpl();
        this.usernameValidator = new UsernameValidator(accountService);
        this.passwordValidator = new PasswordValidator();
        this.phoneValidator = new PhoneValidator(accountService);
        this.ageValidator = new AgeValidator();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void startApplication() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register (New Account)   2. Login   3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> {
                    System.out.println("Goodbye! 👋");
                    return;
                }
                default -> System.out.println("Invalid choice ❌");
            }
        }
    }

    private void registerUser() {
        try {
            System.out.print("Enter username: ");
            String username = usernameValidator.getValidUsername(scanner.nextLine());
            System.out.print("Enter password: ");
            String password = passwordValidator.getValidPassword(scanner.nextLine());
            System.out.print("Enter phone number: ");
            String phone = phoneValidator.getValidPhone(scanner.nextLine());
            System.out.print("Enter age: ");
            int age = ageValidator.getValidAge(scanner.nextLine());
            Account newAccount = new Account(username, password, phone, age, 0);
            boolean created = accountService.createAccount(newAccount);
            if (created) {
                currentAccount = newAccount;
                System.out.println("Account created successfully ✅");
            } else {
                System.out.println("Username already exists (validator should prevent this) ❌");
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage() + " ❌");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private void loginUser() {
        int attempts = 4;
        while (attempts > 0) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            Account account = accountService.login(username, password);
            if (account != null) {
                currentAccount = account;
                System.out.println("Login successful ✅");
                showAccountMenu();
                return;
            } else {
                attempts--;
                if (attempts > 0)
                    System.out.println("Invalid credentials. Attempts left: " + attempts);
                else
                    System.out.println("Account locked. Contact admin 🔒");
            }
        }
    }

    private void showAccountMenu() {
        while (true) {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. Withdraw   2. Deposit   3. Transfer   4. Account Details   5. Change Password   6. Transaction History   7. Logout");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> withdraw();
                case 2 -> deposit();
                case 3 -> transfer();
                case 4 -> showAccountDetails();
                case 5 -> changePassword();
                case 6 -> showTransactionHistory();
                case 7 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid choice ❌");
            }
        }
    }

    private void withdraw() {
        System.out.print("Amount to withdraw: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        try {
            Account result = accountService.withdraw(currentAccount, amount);
            currentAccount = result;
            System.out.println("Withdrawal successful ✅ New balance: " + currentAccount.getBalance());
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println(e.getMessage() + " ❌");
        }
    }

    private void deposit() {
        System.out.print("Amount to deposit: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        try {
            Account result = accountService.deposit(currentAccount, amount);
            currentAccount = result;
            System.out.println("Deposit successful ✅ New balance: " + currentAccount.getBalance());
        } catch (MinimumAmountException | AccountNotFoundException e) {
            System.out.println(e.getMessage() + " ❌");
        }
    }

    private void transfer() {
        System.out.print("Target username: ");
        String target = scanner.nextLine();
        System.out.print("Amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        try {
            Account result = accountService.transfer(currentAccount, target, amount);
            currentAccount = result;
            System.out.println("Transfer successful ✅");
        } catch (AccountNotFoundException | InsufficientBalanceException | IllegalArgumentException e) {
            System.out.println(e.getMessage() + " ❌");
        }
    }

    private void showAccountDetails() {
        Account acc = accountService.getAccountByUsername(currentAccount.getUsername());
        if (acc != null) {
            String maskedPassword = "*".repeat(acc.getPassword().length());
            System.out.println("Username: " + acc.getUsername());
            System.out.println("Age: " + acc.getAge());
            System.out.println("Phone: " + acc.getPhoneNumber());
            System.out.println("Balance: " + acc.getBalance());
            System.out.println("Password: " + maskedPassword);
        }
    }

    private void changePassword() {
        System.out.print("Enter old password: ");
        String oldPass = scanner.nextLine();
        if (currentAccount.getPassword().equals(oldPass)) {
            try {
                System.out.print("Enter new password: ");
                String newPass = passwordValidator.getValidPassword(scanner.nextLine());
                currentAccount.setPassword(newPass);
                System.out.println("Password changed successfully ✅");
            } catch (ValidationException e) {
                System.out.println(e.getMessage() + " ❌");
            }
        } else {
            System.out.println("Incorrect old password ❌");
        }
    }

    private void showTransactionHistory() {
        System.out.println(EWalletSystem.getInstance().getTransactionHistory().getFormattedHistory());
    }
}