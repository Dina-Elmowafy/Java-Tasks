package V2.model;

public class Account {
    private String userName;
    private String password;
    private int age;
    private String phoneNumber;
    private int balance;


    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Account(String userName, String password, int age, String phoneNumber, int balance) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
   @Override
    public String toString()
    {
        return  "Account { " + "userName = "+ userName + '\'' +
        "phoneNumber = " +phoneNumber +'\'' +
                "age = "+ age +'\''+
                "balance = "+ balance ;

    }


}
