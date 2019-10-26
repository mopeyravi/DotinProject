package presentation;

import persistence.LoanType;
import persistence.RealCustomer;
import business.AccountManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class LoadFacilitiesListForCustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        String customerID=request.getParameter("customerID");
        List<RealCustomer> customer=AccountManager.searchRealCustomer(customerID, "", "","");
        List<LoanType> facilities=AccountManager.listAllFacilities();
        request.setAttribute("customer", customer.get(0));
        request.setAttribute("facilities", facilities);
        request.getRequestDispatcher("/htmlPart/open-facilities-file.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
