package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;

import java.util.*;

public class BankDB {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public BankDB() {
    }

    public static void deleteLoanType(Integer loanID) {
        LoanTypeCRUD.deleteLoanType(loanID);
    }

    public static List<LoanType> loadAllLoanTypes() {
        return LoanTypeCRUD.loadAllLoanTypes();
    }

    public static LoanType loadLoanByTypeName(String typeName) {
        return LoanTypeCRUD.loadLoanByTypeName(typeName);
    }

    public static long addLoan(String typeName, int interestRate, HashSet grantConditions) {
        return LoanTypeCRUD.addLoan(typeName,interestRate,grantConditions);
    }

    public static Long addLegalCustomer(String companyName, String economicCode, String submissionDate) {
        return CustomerCRUD.addLegalCustomer(companyName,economicCode,submissionDate);
    }

    public static Long addRealCustomer(String firstName, String lastName, String fatherName, String birthDate, String nationalCode) {
        return CustomerCRUD.addRealCustomer(firstName,lastName,fatherName,birthDate,nationalCode);
    }

    public static List<RealCustomer> loadRealCustomer(Long customerID, String firstName, String lastName, String nationalCode) {
       return CustomerCRUD.listRealCustomer(customerID,firstName,lastName,nationalCode);
    }

    public static List<LegalCustomer> loadLegalCustomer(Long customerID, String companyName, String economicCode) {
        return CustomerCRUD.listLegalCustomer(customerID,companyName,economicCode);
    }

    public static void updateRealCustomer(Long customerID, String firstName, String lastName, String fatherName, String nationalCode, String birthDate) {
        CustomerCRUD.updateRealCustomer(customerID,firstName,lastName,fatherName,nationalCode,birthDate);
    }

    public static void updateLegalCustomer(Long customerID, String companyName, String economicCode, String submitDate) {
        CustomerCRUD.updateLegalCustomer(customerID,companyName,economicCode,submitDate);
    }
    public static Long openNewLoanFile(LoanFile loanFile){
        return LoanFileCRUD.openLoanFile(loanFile);
    }

    public static void deleteRealCustomer(Long customerID) {
        CustomerCRUD.deleteRealCustomer(customerID);
    }

    public static void deleteLegalCustomer(Long customerID) {
        CustomerCRUD.deleteLegalCustomer(customerID);
    }

    public static void main(String args[]){
//        LoanType loanType=loadLoanByTypeName("House");
//        openNewLoanFile(new LoanFile(loanType,4));
        HashSet<GrantCondition> grantConditions=new HashSet<GrantCondition>();
        grantConditions.add(new GrantCondition("شرط1",new Long(12),new Long(15),23,14));
        grantConditions.add(new GrantCondition("شرط1",new Long(12),new Long(15),23,14));
        LoanType loanType=new LoanType("نام",10);
        loanType.setGrantConditions(grantConditions);
        addLoan("نام",10,grantConditions);
//        RealCustomer realCustomer=new RealCustomer("فاطمه", "احمدی", "ابراهیم","1366","2300246570");
//        System.out.print(realCustomer.getFatherName());
//        addRealCustomer("فاطمه", "احمدی", "ابراهیم","1366","2300246570");
    }
}
