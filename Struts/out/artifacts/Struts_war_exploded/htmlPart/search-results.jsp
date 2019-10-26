<%@ page import="persistence.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="persistence.LegalCustomer" %>
<%@ page import="persistence.RealCustomer" %>
<%@ page import="java.util.ArrayList" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<title>سامانه مدیریت بانک</title>
<link rel="Stylesheet" type="text/css" href="http://localhost:8080/htmlPart/style/stylemain.css">
<link rel="Stylesheet" type="text/css" href="http://localhost:8080/htmlPart/style/style.css">
<script src="http://localhost:8080/htmlPart/js/jquery.js" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function () {
        //When page loads...
        $(".tab_content").hide(); //Hide all content
        $("ul.tabs li:first").addClass("active").show(); //Activate first tab
        $(".tab_content:first").show(); //Show first tab content
        //On Click Event
        $("ul.tabs li").click(function () {
            $("ul.tabs li").removeClass("active"); //Remove any "active" class
            $(this).addClass("active"); //Add "active" class to selected tab
            $(".tab_content").hide(); //Hide all tab content
            var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
            $(activeTab).fadeIn(); //Fade in the active ID content
            return false;
        });
    });
</script>
<style type="text/css"></style>

<div id="mainBar">
    <div id="topBar">
        <br>
        <a id="homeLink" href="http://localhost:8080/htmlPart/Home.jsp"><b>سامانه مدیریت بانک </b></a>
    </div>
    <div id="middleBar">
        <div id="rightBar">
            <div id="verticalLinks">
                <ul id="vertLinks">
                    <a class="vertLink" href="http://localhost:8080/htmlPart/new-user.jsp">تعریف مشتری جدید</a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/search-user.jsp">جستجو </a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/banking-facilities.jsp">تسهیلات بانکی</a>
                </ul>
            </div>
        </div>
        <div id="leftBar">
            <%request.setCharacterEncoding("utf8");%>
            <div id="table">
                <%!int i;%>
                <%if (!request.getAttribute("customers").toString().isEmpty()) {%>
                <form action="/EditCustomerServlet">
                    <table id="searchTable">
                        <tbody>
                        <tr class="rows" id="ID0">
                            <%if (request.getAttribute("customerType").equals("LEGAL")) {%>
                            <%!ArrayList<LegalCustomer> legalCustomers;%>
                            <%legalCustomers = (ArrayList<LegalCustomer>) request.getAttribute("customers");%>
                            <th>حذف</th>
                            <th>ویرایش</th>
                            <th>نام شرکت</th>
                            <th>کد اقتصادی</th>
                            <th>تاریخ ثبت</th>
                            <th>شماره مشتری</th>
                        </tr>
                        </tbody>
                        <%for (i = 0; i < legalCustomers.size(); i++) { %>
                        <tbody>
                        <tr class="rows" id="ID<%legalCustomers.get(i).getCustomerID();%>">
                            <td>
                                <button type="submit" name="DeleteBtn"
                                        value="Delete<%=legalCustomers.get(i).getCustomerID()%>">حذف مشتری
                                </button>
                            </td>
                            <td>
                                <button type="submit" name="EditBtn"
                                        value="Edit<%=legalCustomers.get(i).getCustomerID()%>">ویرایش مشتری
                                </button>
                            </td>
                            <td><input type="text" size="5" name="companyName"
                                       value="<%=legalCustomers.get(i).getCompanyName()%>"></td>
                            <td><input type="text" size="6" name="economicCode"
                                       value="<%=legalCustomers.get(i).getEconomicCode()%>"></td>
                            <td><input type="text" size="5" name="submissionDate"
                                       value="<%=legalCustomers.get(i).getSubmissionDate()%>"></td>
                            <td><input type="text" size="4" name="ID"
                                       value="<%=legalCustomers.get(i).getCustomerID()%>"
                                       readonly></td>
                        </tr>
                        </tbody>
                        <%
                            }
                        } else {
                        %>
                        <%!ArrayList<RealCustomer> realCustomers;%>
                        <%realCustomers = (ArrayList<RealCustomer>) request.getAttribute("customers");%>
                        <th>حذف</th>
                        <th>ویرایش</th>
                        <th>نام</th>
                        <th>نام خانوادگی</th>
                        <th>نام پدر</th>
                        <th>تاریخ تولد</th>
                        <th>کد ملی</th>
                        <th>شماره مشتری</th>
                        </tr>
                        </tbody>
                        <%for (i = 0; i < realCustomers.size(); i++) { %>
                        <tbody>
                        <tr class="rows" id="ID<%=realCustomers.get(i).getCustomerID()%>">
                            <td>
                                <button type="submit" name="DeleteBtn"
                                        value="Delete<%=realCustomers.get(i).getCustomerID()%>"
                                        value="Delete">حذف مشتری
                                </button>
                            </td>
                            <td>
                                <button type="submit" name="EditBtn"
                                        value="Edit<%=realCustomers.get(i).getCustomerID()%>" value="Edit">
                                    ویرایش مشتری
                                </button>
                            </td>
                            <td><input type="text" size="5" name="firstName"
                                       value="<%=realCustomers.get(i).getFirstName()%>"></td>
                            <td><input type="text" size="6" name="lastName"
                                       value="<%=realCustomers.get(i).getLastName()%>">
                            </td>
                            <td><input type="text" size="5" name="fatherName"
                                       value="<%=realCustomers.get(i).getFatherName()%>"></td>
                            <td><input type="text" size="6" name="birthDate"
                                       value="<%=realCustomers.get(i).getBirthDate()%>"></td>
                            <td><input type="text" size="5" name="nationalCode"
                                       value="<%=realCustomers.get(i).getNationalCode()%>"></td>
                            <td><input type="text" size="4" name="ID"
                                       value="<%=realCustomers.get(i).getCustomerID()%>"
                                       readonly></td>
                        </tr>
                        </tbody>
                        <%
                                }
                            }
                        %>
                    </table>
                </form>
                <%}%>
            </div>
        </div>
    </div>
</div>
</body>
</html>