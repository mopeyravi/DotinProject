package main.java.persistence;

public class RealCustomer extends Customer{

    private final String customerType = "REAL";
    private String firstName;
    private String lastName;
    private String fatherName;
    private String birthDate;
    private String nationalCode;

    public RealCustomer() {
    }

    public RealCustomer(String firstName, String lastName, String fatherName, String birthDate, String nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.nationalCode = nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getCustomerType() {
        return customerType;
    }
}
