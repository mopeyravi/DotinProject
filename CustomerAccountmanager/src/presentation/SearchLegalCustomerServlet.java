package presentation;


import persistence.LegalCustomer;
import business.AccountManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;


//

public class SearchLegalCustomerServlet extends HttpServlet {
    public void init() {

    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String companyName=request.getParameter("companyname");
        String economicCode=request.getParameter("economiccode");
        String customerID=request.getParameter("customerID");
        AccountManager manager=new AccountManager();
        ArrayList<LegalCustomer> customers=manager.searchLegalCustomer(companyName, economicCode, customerID);
        updateHtmlFile("D:\\Projects\\CustomerAccountmanager\\out\\artifacts\\CustomerAccountmanager_war_exploded\\htmlPart\\searchResults.html",customers);
        String site = request.getContextPath() + "/htmlPart/searchResults.html";
        response.sendRedirect(site);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public static void updateHtmlFile(String fileLocation,ArrayList<LegalCustomer> customers) {
        Document htmlFile;
        try {
            htmlFile = Jsoup.parse(new File(fileLocation), "ISO-8859-1");
            Elements rows = htmlFile.getElementsByTag("tbody");
            for (Element row : rows) {
                row.remove();
            }

            Element table = htmlFile.getElementById("searchTable");
            table.append("<tr class=\"rows\" id=\"ID0\"> \n" +
                    "\t\t\t\n" +
                    "\t\t\t<th>Delete Button</th>\n" +
                    "\t\t\t<th>Edit Button</th>\n" +
                    "\t\t\t<th>Company Name</th> \n" +
                    "\t\t\t<th>Submission Date</th> \n" +
                    "\t\t\t<th>Customer ID</th> \n" +
                    "</tr>");
            for (LegalCustomer customer : customers) {
                table.append("<tr class=\"rows\" id=\"ID" + customer.id + "\"> \n" +
                        "\t\t\t\t<td><button type=\"submit\" name=\"DeleteBtn\" value=\"Delete"+customer.id+"\" >Delete Customer</button></td>\n" +
                        "\t\t\t\t<td><button type=\"submit\" name=\"EditBtn\" value=\"Edit"+customer.id+"\" >Edit Customer</button></td>\n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"companyName\" value=\"" + customer.companyName + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"6\" name=\"economicCode\" value=\"" + customer.economicCode + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"5\" name=\"submissionDate\" value=\"" + customer.submissionDate + "\" /></td> \n" +
                        "\t\t\t\t<td><input type=\"text\" size=\"4\" name=\"ID\" value=\"" + customer.id + "\" readonly /></td> \n" +
                        "</tr>");

            }
            BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation), "UTF-8"));
            htmlWriter.write(htmlFile.toString());
            htmlWriter.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    public static void main(String args[])
    {
        System.out.println("Start..");
//        updateHtmlFile("D:\\Projects\\Maven_Project\\AccountPresentation\\html\\successpage.html","10","D:\\Projects\\Maven_Project\\AccountPresentation\\html\\successpage_2.html");
        System.out.println("Stop..");
    }

}
