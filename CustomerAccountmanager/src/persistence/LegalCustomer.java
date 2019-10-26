package persistence;

public class LegalCustomer {
    public final String type = "LEGAL";
    public String id;
    public String companyName;
    public String economicCode;
    public String submissionDate;

    public LegalCustomer(String companyName, String economicCode, String submissionDate, String id) {
        this.companyName = companyName;
        this.economicCode = economicCode;
        this.submissionDate = submissionDate;
        this.id = id;
    }


    public void setId(String id) {
        this.id = id;
    }
}
