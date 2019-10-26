package main.java.Model;

/**
 * Created by DOTIN SCHOOL 3 on 4/29/2015.
 */
public class CreateNewLegalCustomerAction {
    private String companyName;
    private String economicCode;
    private String submissionDateDate;
    private Long id;

    public String execute() throws Exception{
//        id = AccountManager.insertNewLegalCustomer(companyName, economicCode, submissionDateDate);
        id=new Long(10);
        return "success";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    public void setSubmissionDateDate(String submissionDateDate) {
        this.submissionDateDate = submissionDateDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public String getSubmissionDateDate() {
        return submissionDateDate;
    }

    public Long getId() {
        return id;
    }
}
