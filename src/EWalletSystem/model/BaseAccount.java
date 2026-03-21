package EWalletSystem.model;



// Abstract class representing a base account
public abstract class BaseAccount {

    protected String username;
    protected String password;
    protected String phone;
    protected int age;
    protected double balance;
    protected boolean isActive = true;

    public BaseAccount(String username, String password, String phone, int age) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.age = age;
        this.balance = 0;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public boolean isActive() { return isActive; }

    public void setPassword(String password) { this.password = password; }
    public void setBalance(double balance) { this.balance = balance; }
    public void deactivate() { isActive = false; }

    // -------- VALIDATION --------
    public static boolean validateUsername(String username) {
        return username.matches("[A-Z][a-zA-Z]*");
    }

    public static boolean validatePhone(String phone) {
        return phone.matches("01[0125]\\d{8}");
    }

    public static boolean validatePassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$");
    }

    public static boolean validateAge(int age) {
        return age >= 18;
    }
}