import TransactionExceptions.InvalidTransactionException;
import TransactionExceptions.TransactionTypeNotFoundException;

import java.io.Serializable;
import java.math.BigDecimal;


public class Transaction implements Serializable {
    private String id;
    private String type;
    private BigDecimal amount;
    private String deposit;

    public Transaction(String id, String type, BigDecimal amount, String deposit) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.deposit = deposit;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getDeposit() {
        return deposit;
    }

    public Deposit Do(Deposit dep) {
        BigDecimal initialBalance = dep.getInitialBalance();
        if (this.type.equals("deposit")) {
            initialBalance = initialBalance.add(this.amount);

        } else if (this.type.equals("withdraw")) {
            initialBalance = initialBalance.subtract(this.amount);
        } else {
            System.out.print("ERROR: Not A Valid Transaction!");
            return dep;
        }
        return new Deposit(dep.getCustomer(), dep.getId(), initialBalance, dep.getUpperBound());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isValid(Deposit dep) {
        if (this.type.equals("deposit")) {
            if (dep.getInitialBalance().add(this.amount).compareTo(dep.getUpperBound()) < 0) {
                return true;
            } else {
                try {
                    throw new InvalidTransactionException();
                } catch (InvalidTransactionException ex) {
                    System.out.println(ex.toString());
                    return false;
                }

            }

        } else if (this.type.equals("withdraw")) {
            if (dep.getInitialBalance().subtract(this.amount).compareTo(BigDecimal.ZERO) >= 0) {
                return true;
            } else {
                try {
                 //   System.out.println("Deposit Amount: "+dep.getInitialBalance()+"Withdraw Amount: "+this.amount);
                    throw new InvalidTransactionException();
                } catch (InvalidTransactionException ex) {
                    System.out.println(ex.toString());
                    return false;
                }

            }

        } else {
            try {
                throw new TransactionTypeNotFoundException();
            } catch (TransactionTypeNotFoundException ex) {
                System.out.println(ex.toString());
                return false;
            }
        }
    }
}
