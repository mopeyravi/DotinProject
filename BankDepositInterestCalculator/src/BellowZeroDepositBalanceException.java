
public class BellowZeroDepositBalanceException extends Exception{
    public BellowZeroDepositBalanceException() {
        super("The DurationDays is invalid: <=0");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
