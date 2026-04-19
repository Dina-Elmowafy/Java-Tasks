package V3.services.Impl;

import V3.model.Account;
import V3.services.AccountService;
import V3.services.ApplicationService;
import V3.services.Impl.AccountServiceImpl;
import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {
    private Account currentAccount;
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public void startApplication() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register (New Account)   2. Login   3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye! 👋");
                    return;
                default:
                    System.out.println("Invalid choice ❌");
            }
        }
    }

    // ================= REGISTER =================

    private void registerUser(Scanner scanner) {

        String username = getValidUsername(scanner);
        String password = getValidPassword(scanner);
        String phone = getValidPhone(scanner);
        int age = getValidAge(scanner);
        int initialBalance = 0;

        Account newAccount = new Account(username, password, phone, age, initialBalance);
        currentAccount =newAccount;

        boolean isCreated = accountService.createAccount(newAccount);

        if (isCreated) {
            System.out.println("Account created successfully ✅");
        } else {
            System.out.println("Username already exists ❌");
        }
    }

    // ================= LOGIN =================

    private void loginUser(Scanner scanner) {

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();
        int counter=4;
        Account loginAttempt = new Account(username, password);

        Account foundAccount = accountService.getAccount(loginAttempt);
        if (foundAccount != null) {
            System.out.println("Login successful ✅");
            currentAccount = foundAccount;

        if(!(foundAccount.getPassword().equals(password)))
        {
           counter--;

        }


            while (true) {
                System.out.println("\n--- Account Menu ---");
                System.out.println("1. Withdraw          2. Deposit");
                System.out.print("Enter your choice: ");

                int userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 1:
                        withdrawAmount();
                        break;
                    case 2:
                        depositAmount();
                        break;
                    default:
                        System.out.println("Invalid number ❌");
                }
            }

        } else {
            System.out.println("Invalid credentials ❌");
        }
    }

    // ================= VALIDATION =================

    private String getValidUsername(Scanner scanner) {

        while (true) {
            try {
                System.out.println("Enter username:");
                String username = scanner.nextLine();

                if (username.trim().isEmpty()) {
                    throw new Exception("Username cannot be empty");
                }
                if(!Character.isUpperCase(username.charAt(0)))
                {
                    throw new Exception("Username Should start with UpperCase");
                }
                for (char ch : username.toCharArray()) {
                    if (!(Character.isLetter(ch))) {
                        throw new Exception("Invalid character in username (only letters allowed)");
                    }
                    boolean isUnique = accountService.getUserName(username);
                    if (!isUnique) {
                        throw new Exception("Username already exists. Please choose a different one.");
                    }
                }

                return username;

            } catch (Exception e) {
                System.out.println(e.getMessage() + " ❌");
            }
        }
    }

    private String getValidPassword(Scanner scanner) {

        while (true) {
            try {
                System.out.println("Enter password:");
                String password = scanner.next();

                if (password.length() < 8) {
                    throw new Exception("Password must be at least 8 characters");
                }

                boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;

                for (char ch : password.toCharArray()) {
                    if (ch >= 'A' && ch <= 'Z') hasUpper = true;
                    else if (ch >= 'a' && ch <= 'z') hasLower = true;
                    else if (ch >= '0' && ch <= '9') hasDigit = true;
                    else hasSpecial = true;
                }

                if (!(hasUpper && hasLower && hasDigit && hasSpecial)) {
                    throw new Exception("Weak password! Must contain uppercase, lowercase, numbers, and special characters.");
                }

                return password;

            } catch (Exception e) {
                System.out.println(e.getMessage() + " ❌");
            }
        }
    }

    private String getValidPhone(Scanner scanner) {

        while (true) {
            try {
                System.out.println("Enter phone number:");
                String phone = scanner.next();
                boolean isPhoneNumberUnique =accountService.getPhoneNumber(phone);
                if(isPhoneNumberUnique==false) {
                    throw new Exception("we have the same phoneNumber in another account");
                }
                    if (phone.length() != 11) {
                        throw new Exception("Phone number must be exactly 11 digits");
                    }

                    String prefix = phone.substring(0, 3);

                    if (!(prefix.equals("010") || prefix.equals("011") ||
                            prefix.equals("012") || prefix.equals("015"))) {
                        throw new Exception("Invalid phone prefix (must be in Egypt format )");
                    }

                    return phone;

            } catch (Exception e) {
                System.out.println(e.getMessage() + " ❌");
            }
        }
    }

    private int getValidAge(Scanner scanner) {

        System.out.println("Enter age:");
        int age = scanner.nextInt();

        if (age < 18) {
            System.out.println("Age must be 16 or older ❌ Program will exit.");
            System.exit(0); // 👈 يوقف البرنامج بالكامل (تركتها كما طلبتِ)
        }

        return age;
    }

    // ================= TRANSACTIONS =================

    private void withdrawAmount() {

        System.out.println("Enter the amount you wish to withdraw:");
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();

        if (amount > currentAccount.getBalance()) { // تم تصحيح الجملة الإملائية فقط هنا
            System.out.println("Not enough balance ❌");
        } else {
            currentAccount.setBalance(currentAccount.getBalance() - amount);
            System.out.println("Withdrawal successful ✅");
        }
    }

    private void depositAmount() {
        System.out.println("Enter the amount you wish to deposit:");
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();

        currentAccount.setBalance(currentAccount.getBalance() + amount);
        System.out.println("Deposit successful ✅");
    }
}