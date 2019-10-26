import java.math.BigDecimal;

public class Deposit {
    private String customer;
    private String id;
    private BigDecimal initialBalance;
    private BigDecimal upperBound;

    public Deposit(String customer, String id, BigDecimal initialBalance, BigDecimal upperBound) {
        this.customer = customer;
        this.id = id;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }

    public String getCustomer() {
        return customer;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        return "{\n\tCustomer: " + customer + "\n\tid: " + id + "\n\tinitialBalance: " + initialBalance + "\n\tupperBound: " + upperBound + "\n},\n";
    }
}
