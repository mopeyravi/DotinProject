package presentation;

import business.AccountManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class CreateNewRealCustomerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String fatherName = request.getParameter("fathername");
        String nationalCode = request.getParameter("nationalcode");
        String birthDate = request.getParameter("birthdate");
        Long id = AccountManager.insertNewRealCustomer(firstName, lastName, fatherName, nationalCode, birthDate);
        request.setAttribute("ID", id);
        request.getRequestDispatcher("/htmlPart/success-page.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
