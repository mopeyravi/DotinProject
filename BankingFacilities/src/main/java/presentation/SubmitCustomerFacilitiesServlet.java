package presentation;

import persistence.LoanFile;
import persistence.LoanType;
import persistence.GrantCondition;
import persistence.RealCustomer;
import business.AccountManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SubmitCustomerFacilitiesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String customerID = request.getParameter("customerID");
        List<RealCustomer> customers = AccountManager.searchRealCustomer(customerID, "", "", "");
        LoanType loanType = AccountManager.listFacilities(request.getParameter("AvailableFacilities"));
        String customerTime=request.getParameter("agreementTime");
        String customerAmount=request.getParameter("agreementAmount");
        ArrayList<GrantCondition> grantConditions=new ArrayList<GrantCondition>(loanType.getGrantConditions());
        boolean flagAmountCondition=false, flagTimeCondition=false;
        for (GrantCondition grantCondition : grantConditions) {
            if ((grantCondition.getMaxAgreementAmount().compareTo(new Long(customerAmount)) > 0) && (grantCondition.getMinAgreementAmount().compareTo(new Long(customerAmount)) < 0)) {
                flagAmountCondition = true;
            }
            if ((grantCondition.getMaxAgreementTime() > Integer.valueOf(customerTime)) && (grantCondition.getMinAgreementTime() < Integer.valueOf(customerTime))) {
                flagTimeCondition = true;
            }
            if (flagAmountCondition & flagTimeCondition) {
                break;
            }
            flagAmountCondition = false;
            flagTimeCondition = false;
        }
        if(flagAmountCondition&&flagTimeCondition){
            AccountManager.openNewLoanFile(new LoanFile(loanType,new Long(customerID)));
        }
        request.setAttribute("customer", customers.get(0));
        request.setAttribute("IsValidFacility", flagAmountCondition&&flagTimeCondition);
        request.getRequestDispatcher("/htmlPart/customer-facilities-submission-result.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
