package EWalletSystem.model;

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

    // -------- GETTERS --------
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public boolean isActive() { return isActive; }

    // -------- SETTERS --------
    public void setPassword(String password) { this.password = password; }
    public void setBalance(double balance) { this.balance = balance; }
    public void deactivate() { isActive = false; }

    // -------- VALIDATION METHODS --------
    public static boolean validateUsername(String username) {
        // Must start with uppercase and contain only letters
        return username.matches("[A-Z][a-zA-Z]*");
    }

    public static boolean validatePhone(String phone) {
        // Egyptian phone format: 11 digits, starts with 010,011,012,015
        return phone.matches("01[0125]\\d{8}");
    }

    public static boolean validatePassword(String password) {
        // Minimum 6 chars, at least 1 digit, 1 uppercase, 1 lowercase
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$");
    }

    public static boolean validateAge(int age) {
        return age >= 18;
    }
}