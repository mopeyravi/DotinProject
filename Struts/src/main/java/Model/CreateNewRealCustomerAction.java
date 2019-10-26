package main.java.Model;

public class CreateNewRealCustomerAction {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nationalCode;
    private String birthDate;
    private Long id;

    public String execute() throws Exception{
//        id = AccountManager.insertNewRealCustomer(firstName, lastName, fatherName, nationalCode, birthDate);
        id=new Long(11);
        return "success";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Long getId() {
        return id;
    }
}
