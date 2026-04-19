package V2.model;

import java.util.ArrayList;
import java.util.List;

public class EwalletSystem {

    private final String name ="V2 Of DinaEwalletSystem";
    List<Account> accounts =new ArrayList();

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
