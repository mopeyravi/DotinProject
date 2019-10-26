package presentation;

import business.AccountManager;
import persistence.LegalCustomer;
import persistence.RealCustomer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EditCustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
        Map<String, String[]> parameterMap = request.getParameterMap();
        int customerTableIndx;
        String customerID;
        if (parameterNames.contains("firstName"))//Real
        {
            List<RealCustomer> customers = new ArrayList<RealCustomer>();
            for (int i = 0; i < Arrays.asList(parameterMap.get("firstName")).size(); i++) {
                RealCustomer realCustomer=new RealCustomer(parameterMap.get("firstName")[i], parameterMap.get("lastName")[i], parameterMap.get("fatherName")[i], parameterMap.get("birthDate")[i], parameterMap.get("nationalCode")[i]);
                realCustomer.setCustomerID(Integer.parseInt(parameterMap.get("ID")[i]));
                customers.add(realCustomer);
            }
            if (parameterNames.contains("DeleteBtn")) {
                customerID = request.getParameter("DeleteBtn").replace("Delete", "");
                customerTableIndx = Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                AccountManager.deleteRealCustomer(customerID);
                customers.remove(customerTableIndx);

            } else if (parameterNames.contains("EditBtn")) {//Edit
                customerID = request.getParameter("EditBtn").replace("Edit", "");
                customerTableIndx = Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                AccountManager.updateRealCustomer(customerID, customers.get(customerTableIndx).getFirstName(), customers.get(customerTableIndx).getLastName(), customers.get(customerTableIndx).getFatherName(), customers.get(customerTableIndx).getNationalCode(), customers.get(customerTableIndx).getBirthDate());
            }
            request.setAttribute("customers", customers);
            request.setAttribute("customerType", "REAL");
            request.getRequestDispatcher("/htmlPart/search-results.jsp").forward(request, response);
        } else if (parameterNames.contains("companyName"))//Legal
        {
            List<LegalCustomer> customers = new ArrayList<LegalCustomer>();

            for (int i = 0; i < Arrays.asList(parameterMap.get("companyName")).size(); i++) {
                LegalCustomer legalCustomer=new LegalCustomer(parameterMap.get("companyName")[i], parameterMap.get("economicCode")[i], parameterMap.get("submissionDate")[i]);
                legalCustomer.setCustomerID(new Long(parameterMap.get("ID")[i]));
                customers.add(legalCustomer);

            }
            if (parameterNames.contains("DeleteBtn")) {
                customerID = request.getParameter("DeleteBtn").replace("Delete", "");
                customerTableIndx = Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                AccountManager.deleteLegalCustomer(customerID);
                customers.remove(customerTableIndx);

            } else if (parameterNames.contains("EditBtn")) {//Edit
                customerID = request.getParameter("EditBtn").replace("Edit", "");
                customerTableIndx = Arrays.asList(parameterMap.get("ID")).indexOf(customerID);
                AccountManager.updateLegalCustomer(customerID, customers.get(customerTableIndx).getCompanyName(), customers.get(customerTableIndx).getEconomicCode(), customers.get(customerTableIndx).getSubmissionDate());
            }
            request.setAttribute("customers", customers);
            request.setAttribute("customerType", "LEGAL");
            request.getRequestDispatcher("/htmlPart/search-results.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
