package presentation;

import persistence.LegalCustomer;
import persistence.RealCustomer;
import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import business.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


//

public class EditCustomerServlet extends HttpServlet {
    public void init() {

    }

    AccountManager manager = new AccountManager();

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
        System.out.println(new File(".").getCanonicalPath());
        String fileLoc = "D:\\Projects\\CustomerAccountmanager\\out\\artifacts\\CustomerAccountmanager_war_exploded\\htmlPart\\searchResults.html";
        Map<String ,String[]> parameterMap=request.getParameterMap();
        int customerTableIndx;
        if (parameterNames.contains("firstName"))//Real
        {
            String firstName;
            String lastName;
            String fatherName;
            String nationalCode;
            String birthDate;
            String customerID;
            if (parameterNames.contains("DeleteBtn")) {
                customerID=request.getParameter("DeleteBtn").replace("Delete","");
                customerTableIndx =Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                firstName=parameterMap.get("firstName")[customerTableIndx];
                lastName = parameterMap.get("lastName")[customerTableIndx];
                fatherName = parameterMap.get("fatherName")[customerTableIndx];
                nationalCode = parameterMap.get("nationalCode")[customerTableIndx];
                birthDate = parameterMap.get("birthDate")[customerTableIndx];
                manager.deleteCustomer(customerID);
                updateHtmlFileReal(fileLoc, customerID, fileLoc, "Delete", new RealCustomer(customerID, firstName, lastName, fatherName, birthDate, nationalCode));
            } else if (parameterNames.contains("EditBtn")) {//Edit
                customerID=request.getParameter("EditBtn").replace("Edit","");
                customerTableIndx =Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                firstName=parameterMap.get("firstName")[customerTableIndx];
                lastName = parameterMap.get("lastName")[customerTableIndx];
                fatherName = parameterMap.get("fatherName")[customerTableIndx];
                nationalCode = parameterMap.get("nationalCode")[customerTableIndx];
                birthDate = parameterMap.get("birthDate")[customerTableIndx];
                manager.updateRealCustomer(customerID, firstName, lastName, fatherName, nationalCode, birthDate);
                updateHtmlFileReal(fileLoc, customerID, fileLoc, "Update", new RealCustomer(customerID, firstName, lastName, fatherName, birthDate, nationalCode));
            }


        } else if (parameterNames.contains("companyName"))//Legal
        {
            String companyName;
            String economicCode;
            String submissionDateDate;
            String customerID;
            if (parameterNames.contains("DeleteBtn")) {
                customerID = request.getParameter("DeleteBtn").replace("Delete", "");
                customerTableIndx = Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                companyName = parameterMap.get("companyName")[customerTableIndx];
                economicCode = parameterMap.get("economicCode")[customerTableIndx];
                submissionDateDate = parameterMap.get("submissionDate")[customerTableIndx];
                manager.deleteCustomer(customerID);
                updateHtmlFileLegal(fileLoc, customerID, fileLoc, "Delete", new LegalCustomer(companyName, economicCode, submissionDateDate, customerID));
            } else if (parameterNames.contains("EditBtn")) {//Edit
                customerID = request.getParameter("EditBtn").replace("Edit", "");
                customerTableIndx = Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                companyName = parameterMap.get("companyName")[customerTableIndx];
                economicCode = parameterMap.get("economicCode")[customerTableIndx];
                submissionDateDate = parameterMap.get("submissionDate")[customerTableIndx];
                manager.updateLegalCustomer(customerID, companyName, economicCode, submissionDateDate);
                updateHtmlFileLegal(fileLoc, customerID, fileLoc, "Update", new LegalCustomer(companyName, economicCode, submissionDateDate, customerID));
            }
        }


        String site = request.getContextPath() + "/htmlPart/searchResults.html";
        response.sendRedirect(site);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public static void updateHtmlFileReal(String fileLocation, String id, String fileLocTarget, String action, RealCustomer customer) {

        Document htmlFile;
        try {
            htmlFile = Jsoup.parse(new File(fileLocation), "ISO-8859-1");
            Element row = htmlFile.getElementById("ID" + id);
            row.remove();
            if (action.equals("Update")) {
                Element table = htmlFile.getElementById("searchTable");
                table.append("<tr class=\"rows\" id=\"ID" + customer.id + "\"> \n" +
                        "\t\t\t\t<td><button type=\"submit\"  name=\"DeleteBtn\" value=\"Delete"+customer.id+"\" >Delete Customer</button></td>\n" +
                        "\t\t\t\t<td><button type=\"submit\"  id=editBtn"+customer.id+"value=\"Edit"+customer.id+"\" >Edit Customer</button></td>\n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"firstName\" value=\"" + customer.firstName + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"lastName\" value=\"" + customer.lastName + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"fatherName\" value=\"" + customer.fatherName + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"6\" name=\"nationalCode\" value=\"" + customer.nationalCode + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"birthDate\" value=\"" + customer.birthDate + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"4\" name=\"ID\" value=\"" + customer.id + "\" readonly/></td> \n" +
                        "        </tr>");
            }


            BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocTarget), "UTF-8"));
            htmlWriter.write(htmlFile.toString());
            htmlWriter.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void updateHtmlFileLegal(String fileLocation, String id, String fileLocTarget, String action, LegalCustomer customer) {

        Document htmlFile ;
        try {
            htmlFile = Jsoup.parse(new File(fileLocation), "ISO-8859-1");
            Element row = htmlFile.getElementById("ID" + id);
            row.remove();
            if (action.equals("Update")) {
                Element table = htmlFile.getElementById("searchTable");
                table.append("<tr class=\"rows\" id=\"ID" + customer.id + "\"> \n" +
                        "\t\t\t\t<td><button type=\"submit\" id=delBtn"+customer.id+" name=\"DeleteBtn\" value=\"Delete\" >Delete Customer</button></td>\n" +
                        "\t\t\t\t<td><button type=\"submit\" id=editBtn"+customer.id+ " name=\"EditBtn\" value=\"Edit\" >Edit Customer</button></td>\n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"companyName\" value=\"" + customer.companyName + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"6\" name=\"economicCode\" value=\"" + customer.economicCode + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"submissionDate\" value=\"" + customer.submissionDate + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"4\" name=\"ID\" value=\"" + customer.id + "\" readonly/></td> \n" +
                        "</tr>");
            }


            BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocTarget), "UTF-8"));
            htmlWriter.write(htmlFile.toString());
            htmlWriter.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void main(String args[]) {
        System.out.println("Start..");
//        updateHtmlFile("D:\\Projects\\Maven_Project\\AccountPresentation\\html\\successpage.html","10","D:\\Projects\\Maven_Project\\AccountPresentation\\html\\successpage_2.html");
        System.out.println("Stop..");
    }

}
