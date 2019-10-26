package persistence;

public class Customer {
    private String customerType;
    private long customerID;

    protected Customer(String customerType) {
        this.customerType = customerType;
    }

    protected Customer() {
    }

    public long getCustomerID() {
        return customerID;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
