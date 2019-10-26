package presentation;

import persistence.LegalCustomer;
import business.AccountManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class SearchLegalCustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        String companyName=request.getParameter("companyname");
        String economicCode=request.getParameter("economiccode");
        String customerID=request.getParameter("customerID");
        List<LegalCustomer> customers=AccountManager.searchLegalCustomer(companyName, economicCode, customerID);
        request.setAttribute("customers", customers);
        request.setAttribute("customerType", "LEGAL");
        request.getRequestDispatcher("/htmlPart/search-results.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
