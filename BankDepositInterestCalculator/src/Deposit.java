import java.math.BigDecimal;
import java.math.RoundingMode;

public class Deposit implements Comparable<Deposit>
{
    private BigDecimal depositBalance;
    private int durationInDays;
    private String customerNumber;
    private BigDecimal payedInterest;
    private DepositType depositType;

    public Deposit()
    {

    }


    public Deposit(String id, BigDecimal db, int dd, DepositType dp)
    {
        depositBalance=db;
        durationInDays=dd;
        customerNumber=id;
        depositType= dp;
    }


    public String getCustomerNumber() {
        return customerNumber;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    public void setPayedInterest(BigDecimal payedInterest) {
        this.payedInterest = payedInterest;
    }

    public int compareTo(Deposit bd) {

        return bd.getPayedInterest().compareTo(this.payedInterest);

    }

    public void calculatePayedInterest()
    {
        BigDecimal mul=getDepositBalance().multiply(new BigDecimal(getDurationInDays()));
        setPayedInterest((new BigDecimal(depositType.getInterestRate()).multiply(mul) ).divide(new BigDecimal(36500),10, RoundingMode.HALF_UP));


    }
}
