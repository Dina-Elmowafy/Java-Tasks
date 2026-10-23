package EWalletSystem;

public class Account {

    private String username;
    private String password;
    private String phone;
    private int age;
    private double balance;
    private boolean isAdmin;
    private boolean isActive;

    public Account(String username, String password, String phone, int age, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.age = age;
        this.isAdmin = isAdmin;
        this.balance = 0;
        this.isActive = true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
