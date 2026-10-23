package EWalletSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        WalletServiceImpl service = new WalletServiceImpl();

        while(true){

            System.out.println("1 Signup");
            System.out.println("2 Login");
            System.out.println("3 Exit");

            int choice = sc.nextInt();

            switch (choice){

                case 1:
                    service.signup();
                    break;

                case 2:

                    Account user = service.login();

                    if(user != null){

                        boolean logout = false;

                        while(!logout){

                            System.out.println("1 Deposit");
                            System.out.println("2 Withdraw");
                            System.out.println("3 Transfer");
                            System.out.println("4 Show Details");
                            System.out.println("5 Change Password");
                            System.out.println("6 Logout");

                            int c = sc.nextInt();

                            switch(c){

                                case 1: service.deposit(user); break;
                                case 2: service.withdraw(user); break;
                                case 3: service.transfer(user); break;
                                case 4: service.showDetails(user); break;
                                case 5: service.changePassword(user); break;
                                case 6: logout = true; break;

                            }
                        }
                    }

                    break;

                case 3:
                    System.exit(0);

            }

        }

    }
}
