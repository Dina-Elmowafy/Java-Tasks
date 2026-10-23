
package V3.model;
import V3.model.Account;
import java.util.ArrayList;
import java.util.List;

public class EWalletSystem {
    private final String systemName = "V3_EWallet_System";
    private List<Account> accountList = new ArrayList<>();

    public String getSystemName() {
        return systemName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}