package V2.Service.impl;

import V2.Service.AccountService1;
import V2.Service.ApplicationService1;
import V2.model.Account;

import java.util.Scanner;

public class ApplicationServiceImpl1 implements ApplicationService1 {

    private AccountService1 accountService = new AccountServiceImpl1(); // instance واحدة
    private Scanner scanner = new Scanner(System.in); // Scanner واحدة مشتركة

    @Override
    public void stateApplication() {
        int counter = 0;
        boolean isExit = false;

        while (!isExit) {
            System.out.println("welcome back");
            System.out.println("1.Sign up   2.Log in   3.Exit");
            System.out.println("please choose feature");

            int choose = scanner.nextInt();

            switch (choose) {
                case 1:
                    signIn();
                    break;
                case 2:
                    logeIn();
                    break;
                case 3:
                    System.out.println("Hi, we are in exit");
                    isExit = true;
                    break;
                default:
                    System.out.println("invalid number");
                    counter++;
            }

            if (counter == 4) {
                System.out.println("please call the admin");
                break;
            }
        }
    }

    private void logeIn() {
        System.out.println("Please enter your UserName: ");
        String userName = scanner.next().trim();

        System.out.println("Please enter your password: ");
        String password = scanner.next().trim();

        Account account = new Account(userName, password);
        boolean isAccountExist = accountService.getAccount(account);

        if (isAccountExist) {
            System.out.println("Login Successfully");
        } else {
            System.out.println("invalid userName or Password");
        }
    }

    private void signIn() {
        System.out.println("Please enter your Name: ");
        String userName = scanner.next().trim();

        System.out.println("Please enter your phone: ");
        String phoneNumber = scanner.next().trim();

        System.out.println("Please enter your password: ");
        String password = scanner.next().trim();

        System.out.println("Please enter your age: ");
        int age = scanner.nextInt();

        Account account = new Account(userName, password, age, phoneNumber, 0);

        boolean isAccountCreated = accountService.creatAccount(account);
        if (isAccountCreated) {
            System.out.println("Account created Successfully....:)");
        } else {
            System.out.println("user name already exists in system....:(");
        }
    }
}