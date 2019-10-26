package presentation;

import org.jsoup.Jsoup;
import business.AccountManager;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class CreateNewRealCustomerServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String fatherName = request.getParameter("fathername");
        String nationalCode = request.getParameter("nationalcode");
        String birthDate = request.getParameter("birthDate");
        Long id = AccountManager.insertNewRealCustomer(firstName, lastName, fatherName, nationalCode, birthDate);
        PrintWriter out=response.getWriter();
        printPageOut(out,id);
//        updateHtmlFile("D:\\Projects\\CustomerAccountmanager\\web\\htmlPart\\successpage.html", Long.toString(id), "D:\\Projects\\CustomerAccountmanager\\web\\htmlPart\\successpage.html");

//        updateHtmlFile("D:\\Projects\\CustomerAccountmanager\\out\\artifacts\\CustomerAccountmanager_war_exploded\\htmlPart\\successpage.html", Long.toString(id));

//        String site = request.getContextPath() + "/htmlPart/successpage.html";
//        response.sendRedirect(site);
    }
    public void printPageOut(PrintWriter out, Long id){
        String pageContent="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                " <head> \n" +
                "  <title>CustomerAccountAutomation</title> \n" +
                "  <!--<link rel=\"http://localhost:8080/htmlPart/style/smoothDivScroll.css\" /> --> \n" +
                "  <link rel=\"Stylesheet\" type=\"text/css\" href=\"http://localhost:8080/htmlPart/style/stylemain.css\"> \n" +
                "  <link rel=\"Stylesheet\" type=\"text/css\" href=\"http://localhost:8080/htmlPart/style/style.css\"> \n" +
                "  <script src=\"http://localhost:8080/htmlPart/js/jquery.js\" type=\"text/javascript\"></script> \n" +
                "  <!--\n" +
                "\t<script src=\"js/jquery.ui.widget.js\" type=\"text/javascript\"></script>\n" +
                "\t<script src=\"js/jquery.smoothDivScroll-1.1-min.js\" type=\"text/javascript\"></script>\n" +
                "\t--> \n" +
                "  <script type=\"text/javascript\">\n" +
                "\t\t$(document).ready(function() {\n" +
                "\t\t\n" +
                "\t\t\t//When page loads...\n" +
                "\t\t\t$(\".tab_content\").hide(); //Hide all content\n" +
                "\t\t\t$(\"ul.tabs li:first\").addClass(\"active\").show(); //Activate first tab\n" +
                "\t\t\t$(\".tab_content:first\").show(); //Show first tab content\n" +
                "\t\t\n" +
                "\t\t\t//On Click Event\n" +
                "\t\t\t$(\"ul.tabs li\").click(function() {\n" +
                "\t\t\n" +
                "\t\t\t\t$(\"ul.tabs li\").removeClass(\"active\"); //Remove any \"active\" class\n" +
                "\t\t\t\t$(this).addClass(\"active\"); //Add \"active\" class to selected tab\n" +
                "\t\t\t\t$(\".tab_content\").hide(); //Hide all tab content\n" +
                "\t\t\n" +
                "\t\t\t\tvar activeTab = $(this).find(\"a\").attr(\"href\"); //Find the href attribute value to identify the active tab + content\n" +
                "\t\t\t\t$(activeTab).fadeIn(); //Fade in the active ID content\n" +
                "\t\t\t\treturn false;\n" +
                "\t\t\t});\n" +
                "\t\t\n" +
                "\t\t});\n" +
                "\t\t\t\n" +
                "\t</script> \n" +
                "  <!-- Styles for my specific scrolling content --> \n" +
                "  <style type=\"text/css\"></style> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <div id=\"mainBar\"> \n" +
                "   <div id=\"topBar\"> \n" +
                "    <br> \n" +
                "    <a id=\"homeLink\" href=\"Home.html\"><b>Customer Account Manager </b></a> \n" +
                "   </div> \n" +
                "   <div id=\"middleBar\"> \n" +
                "    <div id=\"rightBar\"> \n" +
                "     <div id=\"verticalLinks\"> \n" +
                "      <ul id=\"vertLinks\"> \n" +
                "       <a class=\"vertLink\" href=\"newUser.html\">Create New User</a> \n" +
                "       <a class=\"vertLink\" href=\"searchUser.html\">Search </a> \n" +
                "      </ul> \n" +
                "     </div> \n" +
                "    </div> \n" +
                "    <div id=\"leftBar\">\n" +
                "     <p id=\"CustomerID\">The Customer ID is: "+id+"</p>  \n" +
                "     <p>Congrats..!</p> \n" +
                "     <p> Account Created Successfully! </p> \n" +
                "     <form action=\"http://localhost:8080/htmlPart/Home_welcome.html\" align=\"center\"> \n" +
                "      <ul> \n" +
                "       <input type=\"submit\" value=\"OK\"> \n" +
                "      </ul> \n" +
                "     </form> \n" +
                "    </div> \n" +
                "   </div> \n" +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
        out.println(pageContent);
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public static void updateHtmlFile(String fileLocation, String id) {


        Document htmlFile;
        try {
            htmlFile = Jsoup.parse(new File(fileLocation), "ISO-8859-1");
            Element element = htmlFile.getElementById("CustomerID");
            if (element!=null) {
                element.remove();
            }

            Element div = htmlFile.getElementById("leftBar");
            div.prepend("<p id=\"CustomerID\">The Customer ID is:\t" + id + "</p>");
            BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLocation), "UTF-8"));
            htmlWriter.write(htmlFile.toString());
            htmlWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        System.out.println("Start..");
        updateHtmlFile("D:\\Projects\\Maven_Project\\AccountPresentation\\html\\successpage.html", "10");
        System.out.println("Stop..");
    }

}
