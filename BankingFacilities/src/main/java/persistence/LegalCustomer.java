package persistence;

public class LegalCustomer extends Customer{
    private final String customerType = "LEGAL";
    private String companyName;
    private String economicCode;
    private String submissionDate;

    public LegalCustomer() {
    }

    public LegalCustomer(String companyName, String economicCode, String submissionDate) {
        this.companyName = companyName;
        this.economicCode = economicCode;
        this.submissionDate = submissionDate;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public String getCustomerType() {
        return customerType;
    }
}
