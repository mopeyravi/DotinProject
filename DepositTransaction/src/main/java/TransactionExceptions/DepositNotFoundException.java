package TransactionExceptions;

/**
 * Created by DOTIN SCHOOL 3 on 4/7/2015.
 */
public class DepositNotFoundException extends Exception{
    public DepositNotFoundException() {
        super("Deposit Is Not Found!");
    }

    public String toString() {
        return super.toString();
    }
}