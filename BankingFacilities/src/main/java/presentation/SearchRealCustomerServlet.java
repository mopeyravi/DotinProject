package presentation;

import persistence.RealCustomer;
import business.AccountManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class SearchRealCustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String nationalCode=request.getParameter("nationalcode");
        String customerID=request.getParameter("customerID");
        List<RealCustomer> customers=AccountManager.searchRealCustomer(customerID, firstName, lastName,nationalCode);
        request.setAttribute("customers", customers);
        request.setAttribute("customerType", "REAL");
        request.getRequestDispatcher("/htmlPart/search-results.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
