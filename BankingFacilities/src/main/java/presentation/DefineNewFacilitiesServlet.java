package presentation;

import business.AccountManager;
import persistence.GrantCondition;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class DefineNewFacilitiesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        String typeName = request.getParameter("typeName");
        int interestRate = Integer.valueOf(request.getParameter("interestRate"));
        Map<String,String[]> parameterMap=request.getParameterMap();
        ArrayList<GrantCondition> grantConditions=new ArrayList<GrantCondition>();
        String name;
        Long maximumAmount, minimumAmount;
        int maximumTime,minimumTime;
        for(int i=0;i<parameterMap.get("Name_table").length;i++){
            name=parameterMap.get("Name_table")[i];
            maximumAmount=Long.valueOf(parameterMap.get("MaximumAmount_table")[i]);
            minimumAmount=Long.valueOf(parameterMap.get("MinimumAmount_table")[i]);
            maximumTime=Integer.valueOf(parameterMap.get("MaximumTime_table")[i]);
            minimumTime=Integer.valueOf(parameterMap.get("MinimumTime_table")[i]);
            grantConditions.add(new GrantCondition(name,minimumAmount,maximumAmount,minimumTime,maximumTime));
        }
        long id = AccountManager.addNewFacilities(typeName, interestRate, grantConditions);
        request.setAttribute("ID", id);
        request.getRequestDispatcher("/htmlPart/banking-facilities.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
