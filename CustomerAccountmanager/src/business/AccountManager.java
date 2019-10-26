package business;

import persistence.*;
import java.util.ArrayList;


public class AccountManager {



    public static void main(String args[])
    {
        System.out.println(insertNewRealCustomer("Ali","Mani","reza","23456","23455"));
    }
    public ArrayList<RealCustomer> searchRealCustomer(String customerID, String firstName, String lastName, String nationalCode)
    {
        AccountDB.connectToDB();
        ArrayList<RealCustomer> customers= AccountDB.selectRealCustomer(customerID, firstName, lastName, nationalCode);
        AccountDB.disconnectDB();
        return customers;
    }
    public ArrayList<LegalCustomer> searchLegalCustomer(String companyName, String economicCode, String customerID){
        AccountDB.connectToDB();
        ArrayList<LegalCustomer> customers= AccountDB.selectLegalCustomer(companyName, economicCode, customerID);
        AccountDB.disconnectDB();
        return customers;
    }

    public static long insertNewRealCustomer(String firstName, String lastName, String fatherName, String nationalCode, String birthDate)
    {
        AccountDB.connectToDB();
        long id = AccountDB.insertNewRealCustomer(firstName, lastName, fatherName, nationalCode, birthDate);
        AccountDB.disconnectDB();
        return id;
    }

    public long insertNewLegalCustomer(String companyName, String economicCode, String submissionDate)
    {
        AccountDB.connectToDB();
        long id= AccountDB.insertNewLegalCustomer(companyName, economicCode, submissionDate);
        AccountDB.disconnectDB();
        return id;
    }

    public void deleteCustomer(String id)
    {
        AccountDB.connectToDB();
        AccountDB.deleteCustomer(id);
        AccountDB.disconnectDB();
    }

    public void updateRealCustomer(String customerID,String firstName, String lastName, String fatherName, String nationalCode, String birthDate)
    {
        AccountDB.connectToDB();
        AccountDB.updateRealCustomer(customerID, firstName, lastName, fatherName, nationalCode, birthDate);
        AccountDB.disconnectDB();
    }

    public void updateLegalCustomer(String customerID,String companyName, String economicCode, String submitDate)
    {
        AccountDB.connectToDB();
        AccountDB.updateLegalCustomer(customerID, companyName, economicCode, submitDate);
        AccountDB.disconnectDB();
    }


}
