package EWalletSystem.model;

public class Account {
    private String username;
    private String password;
    private String phoneNumber;
    private int age;
    private int balance;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, String phoneNumber, int age, int balance) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.balance = balance;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account{username='" + username + "', balance=" + balance + "}";
    }
}