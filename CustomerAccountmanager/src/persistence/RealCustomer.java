package persistence;

public class RealCustomer {
    public final String type = "REAL";
    public String id;
    public String firstName;
    public String lastName;
    public String fatherName;
    public String birthDate;
    public String nationalCode;

    public RealCustomer(String id, String firstName, String lastName, String fatherName, String birthDate, String nationalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.nationalCode = nationalCode;
    }


    public void setId(String id) {
        this.id = id;
    }
}
