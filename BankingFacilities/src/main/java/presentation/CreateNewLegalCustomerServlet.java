package presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import business.*;

public class CreateNewLegalCustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        String companyName = request.getParameter("companyname");
        String economicCode = request.getParameter("economiccode");
        String submissionDateDate = request.getParameter("submissiondate");
        Long id = AccountManager.insertNewLegalCustomer(companyName, economicCode, submissionDateDate);
        request.setAttribute("ID", id);
        request.getRequestDispatcher("/htmlPart/success-page.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
