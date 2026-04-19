package V1.model;
import java.util.List;
import java.util.ArrayList;

public class EwalletSystem {

    private final String name ="Dina system ";

    private List<Account> accounts= new ArrayList();

    public String getName()
    {
        return name;
    }

    public List<Account> getAccounts(){
        return accounts;
    }
    public void setAccounts(List<Account> accounts){
        this.accounts =accounts;
    }
}
