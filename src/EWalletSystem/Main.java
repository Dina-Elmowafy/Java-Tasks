package EWalletSystem;

import EWalletSystem.model.BaseAccount;
import EWalletSystem.service.WalletServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        WalletServiceImpl service = new WalletServiceImpl();

        while (true) {
            System.out.println("1 Sign Up");
            System.out.println("2 Login");
            System.out.println("3 Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> service.signup();
                case 2 -> {
                    BaseAccount user = service.login();
                    if (user != null) {
                        boolean logout = false;
                        while (!logout) {
                            System.out.println("1 Deposit");
                            System.out.println("2 Withdraw");
                            System.out.println("3 Transfer");
                            System.out.println("4 Show Details");
                            System.out.println("5 Change Password");
                            System.out.println("6 Logout");

                            int option = scanner.nextInt();
                            switch (option) {
                                case 1 -> service.deposit(user);
                                case 2 -> service.withdraw(user);
                                case 3 -> service.transfer(user);
                                case 4 -> service.showDetails(user);
                                case 5 -> service.changePassword(user);
                                case 6 -> logout = true;
                            }
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            }
        }
    }
}