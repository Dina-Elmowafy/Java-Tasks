package EWalletSystem.model;

import EWalletSystem.repository.WalletRepository;

public class AdminAccount extends BaseAccount {
    public AdminAccount(String username, String password, String phone, int age) {
        super(username, password, phone, age);
    }

    public void showAllUsers() {
        WalletRepository.accounts
                .stream()
                .forEach(acc -> System.out.println(acc.getUsername() + " | " + acc.getBalance()));
    }
}
