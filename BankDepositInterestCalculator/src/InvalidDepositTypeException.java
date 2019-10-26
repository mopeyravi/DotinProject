

public class InvalidDepositTypeException extends Exception{
    public InvalidDepositTypeException() {
        super("Deposit Type Is Not Valid!");
    }

    public String toString() {
        return super.toString();
    }
}
