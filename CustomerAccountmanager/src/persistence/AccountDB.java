package persistence;

import java.sql.*;
import java.util.ArrayList;

public class AccountDB {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/customer_account";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Root";

    static Connection conn = null;
    static Statement dbStatement = null;

    public AccountDB() {
    }


    public static void main(String[] args) {
        deleteDB();
        createDB();
        connectToDB();

        deleteTable();
        createTable();
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (dbStatement != null)
                    dbStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }//end main



    public static void deleteDB()
    {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Driver loaded!");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            dbStatement = conn.createStatement();
            System.out.println("Connect Successfully");
            String sqlStatement;
            sqlStatement =  "DROP DATABASE customer_account";
            dbStatement.executeUpdate(sqlStatement);
            System.out.println("Delete Successfully");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void createDB() {

        try {
            System.out.println("Start");
            Class.forName(JDBC_DRIVER);
            System.out.println("Driver loaded!");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            dbStatement = conn.createStatement();
            System.out.println("Connect Successfully");
            String sqlStatement;
            sqlStatement = "CREATE DATABASE CUSTOMER_ACCOUNT";
            dbStatement.executeUpdate(sqlStatement);
            System.out.println("Create Successfully");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void connectToDB() {

        try {
            System.out.println("Start");
            Class.forName(JDBC_DRIVER);
            System.out.println("Driver loaded!");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            dbStatement = conn.createStatement();
            System.out.println("Connect Successfully");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteTable() {
        try {
            String sqlStatement;
            sqlStatement = "DROP TABLE CUSTOMER ";
            dbStatement.executeUpdate(sqlStatement);

            sqlStatement = "DROP TABLE LEGAL_CUSTOMER ";
            dbStatement.executeUpdate(sqlStatement);

            sqlStatement = "DROP TABLE REAL_CUSTOMER ";
            dbStatement.executeUpdate(sqlStatement);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } //end try
    }

    public static void createTable() {
        try {
            String sqlStatement;
            sqlStatement = "CREATE TABLE CUSTOMER " +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    " customerType enum('LEGAL','REAL'))";
            dbStatement.executeUpdate(sqlStatement);

            sqlStatement = "CREATE TABLE LEGAL_CUSTOMER " +
                    "(id INTEGER (255) not NULL, " +
                    " companyName VARCHAR(255)," +
                    "economicCode VARCHAR(255) not NULL, " +
                    " submissionDate VARCHAR(255)," +
                    " PRIMARY KEY ( id,economicCode))";
            dbStatement.executeUpdate(sqlStatement);

            sqlStatement = "CREATE TABLE REAL_CUSTOMER " +
                    "(id INTEGER (255) not NULL, " +
                    "nationalCode VARCHAR(255) not NULL, " +
                    " firstName VARCHAR(255)," +
                    " lastName VARCHAR(255)," +
                    " fatherName VARCHAR(255)," +
                    " birthDate VARCHAR(255)," +
                    " PRIMARY KEY ( id,nationalCode))";
            dbStatement.executeUpdate(sqlStatement);
/*
            sqlStatement = "INSERT INTO CUSTOMER" +
                    "(customerType) VALUES('JURIDICAL')" +
                    "INSERT INTO CUSTOMER" +
                    "(customerType) VALUES('REAL')";
            dbStatement.executeUpdate(sqlStatement);

            sqlStatement = "SELECT id, customerType FROM CUSTOEMR";
            ResultSet resultSet = dbStatement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customerType = resultSet.getString("customerType");

                System.out.println("ID: " + id + "\tCustomer Type: " + customerType);

            }
            resultSet.close();
            */

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } //end try
    }


    public static Long insertNewLegalCustomer(String companyName,  String economicCode,String submissionDate) {
        String sqlStatement;
        try {

            sqlStatement = "INSERT INTO CUSTOMER" +
                    "(customerType) VALUES('LEGAL')";
            dbStatement.executeUpdate(sqlStatement);

//            LAST_INSERT_ID()
            ResultSet res = dbStatement.executeQuery("SELECT LAST_INSERT_ID()");

            if (res.next()) {
                Long customerID = res.getLong("last_insert_id()");
                sqlStatement="INSERT INTO LEGAL_CUSTOMER" +
                        "(id,companyName,economicCode,submissionDate) VALUES(?,?,?,?)";
                PreparedStatement dbStatementPrepared=conn.prepareStatement(sqlStatement);
                dbStatementPrepared.setLong(1, customerID);
                dbStatementPrepared.setString(2,companyName);
                dbStatementPrepared.setString(3,economicCode);
                dbStatementPrepared.setString(4,submissionDate);

                dbStatementPrepared.executeUpdate();
                return customerID;
            } else {
                return (long) -1;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            return (long) -1;
        }
    }


    public static Long insertNewRealCustomer(String firstName, String lastName, String fatherName, String nationalCode, String birthDate) {
        String sqlStatement;
        try {

            sqlStatement = "INSERT INTO CUSTOMER" +
                    "(customerType) VALUES('REAL')";
            dbStatement.executeUpdate(sqlStatement);

//            LAST_INSERT_ID()
            ResultSet res = dbStatement.executeQuery("SELECT LAST_INSERT_ID()");

            if (res.next()) {
                Long customerID = res.getLong("last_insert_id()");
                sqlStatement="INSERT INTO REAL_CUSTOMER" +
                        "(id,firstName,lastName,fatherName,nationalCode,birthDate) VALUES(?,?,?,?,?,?)";

//                sqlStatement = "INSERT INTO REAL_CUSTOMER" +
//                        "(id,firstName,lastName,fatherName,nationalCode,birthDate) VALUES(\"" + customerID + "\",\"" + firstName + "\",\"" + lastName + "\",\"" + fatherName + "\",\"" + nationalCode + "\",\"" + birthDate + "\")";
                PreparedStatement dbStatementPrepared=conn.prepareStatement(sqlStatement);
                dbStatementPrepared.setLong(1,customerID);
                dbStatementPrepared.setString(2,firstName);
                dbStatementPrepared.setString(3,lastName);
                dbStatementPrepared.setString(4,fatherName);
                dbStatementPrepared.setString(5,nationalCode);
                dbStatementPrepared.setString(6,birthDate);
                dbStatementPrepared.executeUpdate();
                return customerID;
            } else {
                return (long) -1;
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            return (long) -1;
        }
    }

    public static ArrayList<LegalCustomer> selectLegalCustomer(String companyName, String economicCode, String customerID) {

        ArrayList<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();

        String sqlStatement = "SELECT * FROM LEGAL_CUSTOMER WHERE ";

        int flag=0;

        if (!customerID.isEmpty()) {
            sqlStatement += "id = ?,";
            flag=1;
        }
        if (!economicCode.isEmpty()) {
            sqlStatement += "economicCode = ?,";
            flag=1;
        }
        if (!companyName.isEmpty()) {
            sqlStatement += "companyName = ?,";
            flag=1;
        }
        if (sqlStatement.endsWith(",")) {
            sqlStatement = sqlStatement.substring(0, sqlStatement.length() - 1);
            flag=1;
        }

        try {
            if(flag==0)
            {
                sqlStatement="SELECT * FROM LEGAL_CUSTOMER";
            }
            PreparedStatement dbStatementPrepared=conn.prepareStatement(sqlStatement);
            int indx=1;
            if(sqlStatement.contains("id"))
            {

                dbStatementPrepared.setString(indx,customerID);
                indx++;

            }
            if(sqlStatement.contains("economicCode"))
            {
                dbStatementPrepared.setString(indx,economicCode);
                indx++;
            }
            if(sqlStatement.contains("companyName"))
            {
                dbStatementPrepared.setString(indx,companyName);
            }
            ResultSet resultSet = dbStatementPrepared.executeQuery();
            while (resultSet.next()) {

                String selected_companyName = resultSet.getString("companyName");
                String selected_economicCode = resultSet.getString("economicCode");
                String selected_submissionDate = resultSet.getString("submissionDate");
                String selected_customerID = resultSet.getString("id");

                legalCustomers.add(new LegalCustomer(selected_companyName,selected_economicCode,selected_submissionDate,selected_customerID));
            }
            return legalCustomers;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return legalCustomers;
        }


    }

    public static ArrayList<RealCustomer> selectRealCustomer(String customerID, String firstName, String lastName, String nationalCode) {

        ArrayList<RealCustomer> realCustomers = new ArrayList<RealCustomer>();

        String sqlStatement = "SELECT * FROM REAL_CUSTOMER WHERE ";

        int flag=0;
        if (!customerID.isEmpty()) {
            sqlStatement += "id = ?,";
            flag=1;
        }
        if (!firstName.isEmpty()) {
            flag=1;
            sqlStatement += "firstName = ?,";
        }
        if (!lastName.isEmpty()) {
            flag=1;
            sqlStatement += "lastName = ?,";
        }
        if (!nationalCode.isEmpty()) {
            flag=1;
            sqlStatement += "nationalCode = ?,";
        }
        if (sqlStatement.endsWith(",")) {
            flag=1;
            sqlStatement = sqlStatement.substring(0, sqlStatement.length() - 1);
        }
        try {
            if(flag==0)
            {
                sqlStatement="SELECT * FROM REAL_CUSTOMER";
            }
            int indx=1;
            PreparedStatement dbStatementprepared=conn.prepareStatement(sqlStatement);
            if(sqlStatement.contains("id")){
                dbStatementprepared.setString(indx,customerID);
                indx++;
            }
            if(sqlStatement.contains("firstName")){
                dbStatementprepared.setString(indx,firstName);
            }
            if(sqlStatement.contains("lastName")){
                dbStatementprepared.setString(indx,lastName);
                indx++;
            }
            if(sqlStatement.contains("nationalCode")){
                dbStatementprepared.setString(indx,nationalCode);
            }

            ResultSet resultSet = dbStatementprepared.executeQuery();
            while (resultSet.next()) {

                String selected_nationalCode = resultSet.getString("nationalCode");
                String selected_firstName = resultSet.getString("firstName");
                String selected_customerID = resultSet.getString("id");

                String selected_lastName = resultSet.getString("lastName");
                String selected_birthDate = resultSet.getString("birthDate");
                String selected_fatherName= resultSet.getString("fatherName");

                realCustomers.add(new RealCustomer(selected_customerID,selected_firstName, selected_lastName, selected_fatherName,selected_birthDate,selected_nationalCode));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return realCustomers;


    }

    public static void disconnectDB(){
        try{
            conn.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public static void deleteCustomer(String customerID)
    {
        try {
            String sqlStatement = "SELECT * FROM CUSTOMER WHERE id=?";
            PreparedStatement dbStatementprepared=conn.prepareStatement(sqlStatement);
            dbStatementprepared.setString(1,customerID);
            ResultSet resultSet=dbStatementprepared.executeQuery();
            if(resultSet.next())
            {
                String type=resultSet.getString("customerType");
                if(type.equals("LEGAL"))
                {
                    sqlStatement = "DELETE FROM LEGAL_CUSTOMER " +
                            "WHERE id = ?";

                }
                else if(type.equals("REAL"))
                {
                    sqlStatement = "DELETE FROM REAL_CUSTOMER " +
                            "WHERE id = ?";
                }
                dbStatementprepared=conn.prepareStatement(sqlStatement);
                dbStatementprepared.setString(1,customerID);
                dbStatementprepared.executeUpdate();

                sqlStatement = "DELETE FROM CUSTOMER " +
                        "WHERE id = ?";
                dbStatementprepared=conn.prepareStatement(sqlStatement);
                dbStatementprepared.setString(1,customerID);
                dbStatementprepared.executeUpdate();
                System.out.println("User OF Type:"+type);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void updateRealCustomer(String customerID,String firstName, String lastName, String fatherName, String nationalCode, String birthDate)
    {

        try {
//            String sqlStatement = "UPDATE REAL_CUSTOMER " +
//                    "SET firstName = \""+firstName+"\", lastName = \""+lastName+"\", fatherName = \""+fatherName+"\", nationalCode = \""+nationalCode+"\", birthDate = \""+birthDate+"\" WHERE id=\""+customerID+"\"";
            String sqlStatement = "UPDATE REAL_CUSTOMER " +
                    "SET firstName = ?, lastName = ?, fatherName = ?, nationalCode = ?, birthDate = ?  WHERE id=?";
            PreparedStatement dbStatementprepared=conn.prepareStatement(sqlStatement);
            dbStatementprepared.setString(1,firstName);
            dbStatementprepared.setString(2,lastName);
            dbStatementprepared.setString(3,fatherName);
            dbStatementprepared.setString(4,nationalCode);
            dbStatementprepared.setString(5,birthDate);
            dbStatementprepared.setString(6,customerID);

            dbStatementprepared.executeUpdate();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public static void updateLegalCustomer(String customerID,String companyName, String economicCode, String submitDate)
    {

        try {
            String sqlStatement = "UPDATE LEGAL_CUSTOMER " +
                    "SET companyName = ?, economicCode= ?, submissionDate= ?  WHERE id=?";

            PreparedStatement dbStatementPrepared=conn.prepareStatement(sqlStatement);
            dbStatementPrepared.setString(1,companyName);
            dbStatementPrepared.setString(2,economicCode);
            dbStatementPrepared.setString(3,submitDate);
            dbStatementPrepared.setString(4,customerID);

            dbStatementPrepared.executeUpdate();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}
