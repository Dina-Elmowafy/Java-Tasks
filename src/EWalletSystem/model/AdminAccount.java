package EWalletSystem.model;

import  EWalletSystem.repository.WalletRepository;
public class AdminAccount extends BaseAccount {

    public AdminAccount(String username, String password, String phone, int age) {
        super(username, password, phone, age);
    }

    // Show all users using Stream
    public void showAllUsers() {
        WalletRepository.getAllAccounts()
                .stream()
                .forEach(acc -> System.out.println(acc.getUsername() + " | " + acc.getBalance()));
    }
}