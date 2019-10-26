package persistence;

public class LoanFile {
    private long id;
    private LoanType loanType;
    private long customerID;

    public LoanFile() {
    }

    public LoanFile(LoanType loanType, long customerID, long id) {
        this.loanType = loanType;
        this.customerID = customerID;
        this.id = id;
    }

    public LoanFile(LoanType loanType, long customerID) {
        this.loanType = loanType;
        this.customerID = customerID;
    }

    public long getId() {
        return id;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }
}
