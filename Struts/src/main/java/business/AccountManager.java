package main.java.business;

import main.java.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AccountManager {

    public static List<RealCustomer> searchRealCustomer(String customerID, String firstName, String lastName, String nationalCode) {
        Long id;
        try{id=Long.valueOf(customerID);}catch (NumberFormatException ex){id= (long) 0;}
        return BankDB.loadRealCustomer(id, firstName, lastName, nationalCode);
    }

    public static List<LegalCustomer> searchLegalCustomer(String companyName, String economicCode, String customerID) {
        Long id;
        try{id=Long.valueOf(customerID);}catch (NumberFormatException ex){id= (long) 0;}
        return BankDB.loadLegalCustomer(id, companyName, economicCode);
    }

    public static long insertNewRealCustomer(String firstName, String lastName, String fatherName, String nationalCode, String birthDate) {
        return BankDB.addRealCustomer(firstName, lastName, fatherName, birthDate, nationalCode);
    }

    public static long insertNewLegalCustomer(String companyName, String economicCode, String submissionDate) {
        return BankDB.addLegalCustomer(companyName, economicCode, submissionDate);

    }

    public static void deleteRealCustomer(String id) {
        BankDB.deleteRealCustomer(Long.valueOf(id));
    }

    public static void deleteLegalCustomer(String id) {
        BankDB.deleteLegalCustomer(Long.valueOf(id));
    }

    public static void updateRealCustomer(String customerID, String firstName, String lastName, String fatherName, String nationalCode, String birthDate) {
        Long id;
        try{id=Long.valueOf(customerID);}catch (NumberFormatException ex){id= (long) 0;}
        BankDB.updateRealCustomer(id, firstName, lastName, fatherName, nationalCode, birthDate);

    }

    public static void updateLegalCustomer(String customerID, String companyName, String economicCode, String submitDate) {
        Long id;
        try{id=Long.valueOf(customerID);}catch (NumberFormatException ex){id= (long) 0;}
        BankDB.updateLegalCustomer(id, companyName, economicCode, submitDate);
    }

    public static void main(String args[]) {
        System.out.println(insertNewRealCustomer("Ali", "Mani", "reza", "23456", "23455"));
    }

    public static List<LoanType> listAllFacilities() {
        return BankDB.loadAllLoanTypes();
    }

    public static LoanType listFacilities(String typeName) {
        return BankDB.loadLoanByTypeName(typeName);
    }

    public static long addNewFacilities(String typeName,int interestRate, ArrayList<GrantCondition> grantConditions){
        return BankDB.addLoan(typeName, interestRate, new HashSet(grantConditions));
    }

    public static void openNewLoanFile(LoanFile loanFile){
        BankDB.openNewLoanFile(loanFile);
    }

}
