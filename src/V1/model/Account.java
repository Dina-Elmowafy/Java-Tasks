package V1.model;

public class Account {

    private String userName;

    private String password;

    private String phoneNumber;

    private float balance;

    private int age;




    public Account(String userName, String password, String phoneNumber,int age ) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.balance =0;

    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
          return "Account{" +
                "userName ='" + userName + '\'' +
                "password ='" + password + '\'' +
                "age ='" + age + '\'' +
                "balance ='" + balance + '\'' +
                "phoneNumber ='" + phoneNumber + '\'' + '}';

}
}
