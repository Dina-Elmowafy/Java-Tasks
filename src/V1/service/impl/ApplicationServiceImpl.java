package V1.service.impl;

import V1.model.Account;
import V1.service.AccountService;
import V1.service.ApplicationService;

import java.awt.image.CropImageFilter;
import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {
  @Override
    public void startApplication() {
      System.out.println("Hi sir----:)");
      int counter = 0;
      while (true) {
          System.out.println("1.sign up     2.loge in     3.Exit");
          System.out.println("please choose feature");
          Scanner scanner = new Scanner(System.in);
          int choose = scanner.nextInt();
          boolean ifExit = false;
          switch (choose) {
              case 1:
                  signUp();
                  break;
              case 2:
                  logeIN();
                  break;
              case 3:
                  System.out.println("welcome in Exit feature");
                  ifExit = true;
                  break;
              default:
                  System.out.println("invalid number");
                  counter++;

          }
          if (ifExit) break;
          if (counter == 4) {
              System.out.println("many time invalid choose pls call the admin");
              break;

          }
      }
  }
    private void signUp()
        {
            Scanner scanner =new Scanner(System.in);
            System.out.println("please input userName");
            String userName=scanner.next();

            System.out.println("please input phoneNumber");
            String phoneNumber=scanner.next();

            System.out.println("please input age");
            int age=scanner.nextInt();

            System.out.println("please input password");
            String password=scanner.next();

            Account account =new Account(userName , password ,phoneNumber, age);

            AccountService accountService = new AccountServiceImp();
            boolean isAccountCreated = accountService.CreateAccount(account);
            if(isAccountCreated)
            {
                System.out.println("Account created success");
            }
            else{ System.out.println("faild created ");}

        }
    private void logeIN()
    {
        Scanner scanner =new Scanner(System.in);
        System.out.println("please input userName");
        String userName=scanner.next();

        System.out.println("please input password");
        String password=scanner.next();

    }

    }

