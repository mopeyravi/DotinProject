package TransactionExceptions;

/**
 * Created by DOTIN SCHOOL 3 on 4/7/2015.
 */
public class TransactionTypeNotFoundException extends Exception {
    public TransactionTypeNotFoundException()
    {
        super("Transaction Type Is Not Found!");
    }
    public String toString()
    {
        return super.toString();
    }

}

