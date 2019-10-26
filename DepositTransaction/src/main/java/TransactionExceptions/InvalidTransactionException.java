package TransactionExceptions;

/**
 * Created by DOTIN SCHOOL 3 on 4/7/2015.
 */
public class InvalidTransactionException extends Exception {
    public InvalidTransactionException() {
        super("Transaction Is Not Valid!");
    }

    public String toString() {
        return super.toString();
    }
}
