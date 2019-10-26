<%@ page import="persistence.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="persistence.LegalCustomer" %>
<%@ page import="persistence.RealCustomer" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<title>CustomerAccountAutomation</title>
<link rel="Stylesheet" type="text/css" href="style/stylemain.css">
<link rel="Stylesheet" type="text/css" href="style/style.css">
<script src="js/jquery.js" type="text/javascript"></script>

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
        <a id="homeLink" href="Home.html"><b>Customer Account Manager </b></a>
    </div>
    <div id="middleBar">
        <div id="rightBar">
            <div id="verticalLinks">
                <ul id="vertLinks">
                    <a class="vertLink" href="newUser.html">Create New User</a>
                    <a class="vertLink" href="searchUser.html">Search </a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/BankingFaclilities.html">Banking
                        Facilities</a>
                </ul>
            </div>
        </div>
        <div id="leftBar">
            <div id="table">
                <%!int i;%>
                <%if (!request.getAttribute("customers").toString().isEmpty()) {%>
                <form action="/EditCustomerServlet">
                    <table id="searchTable">
                        <tbody>
                        <tr class="rows" id="ID0">
                            <%if (request.getAttribute("customerType").equals("LEGAL")) {%>
                            <%!LegalCustomer[] legalCustomers;%>
                            <%legalCustomers = (LegalCustomer[]) request.getAttribute("customers");%>
                            <th>Delete Button</th>
                            <th>Edit Button</th>
                            <th>Company Name</th>
                            <th>Submission Date</th>
                            <th>Customer ID</th>
                        </tr>
                        </tbody>
                        <%for (i = 0; i < legalCustomers.length; i++) { %>
                        <tbody>
                        <tr class="rows" id="ID<%legalCustomers[i].getCustomerID();%>">
                            <td>
                                <button type="submit" name="DeleteBtn" value="Delete">Delete Customer</button>
                            </td>
                            <td>
                                <button type="submit" name="EditBtn" value="Edit">Edit Customer</button>
                            </td>
                            <td><input type="text" size="5" name="companyName"
                                       value="<%legalCustomers[i].getCompanyName();%>"></td>
                            <td><input type="text" size="6" name="economicCode"
                                       value="<%legalCustomers[i].getEconomicCode();%>"></td>
                            <td><input type="text" size="5" name="submissionDate"
                                       value="<%legalCustomers[i].getSubmissionDate();%>"></td>
                            <td><input type="text" size="4" name="ID" value="<%legalCustomers[i].getCustomerID();%>"
                                       readonly></td>
                        </tr>
                        </tbody>
                        <%
                            }
                        } else {
                        %>
                        <%!RealCustomer[] realCustomers;%>
                        <%realCustomers = (RealCustomer[]) request.getAttribute("customers");%>
                        <th>Delete Button</th>
                        <th>Edit Button</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Father Name</th>
                        <th>Birth Date</th>
                        <th>National Code</th>
                        <th>CustomerID</th>
                        </tr>
                        </tbody>
                        <%for (i = 0; i < realCustomers.length; i++) { %>
                        <tbody>
                        <tr class="rows" id="ID<%realCustomers[i].getCustomerID();%>">
                            <td>
                                <button type="submit" name="DeleteBtn" value="Delete">Delete Customer</button>
                            </td>
                            <td>
                                <button type="submit" name="EditBtn" value="Edit">Edit Customer</button>
                            </td>
                            <td><input type="text" size="5" name="firstName"
                                       value="<%realCustomers[i].getFirstName();%>"></td>
                            <td><input type="text" size="6" name="lastName" value="<%realCustomers[i].getLastName();%>">
                            </td>
                            <td><input type="text" size="5" name="fatherName"
                                       value="<%realCustomers[i].getFatherName();%>"></td>
                            <td><input type="text" size="6" name="birthDate"
                                       value="<%realCustomers[i].getBirthDate();%>"></td>
                            <td><input type="text" size="5" name="NationalCode"
                                       value="<%realCustomers[i].getNationalCode();%>"></td>
                            <td><input type="text" size="4" name="ID" value="<%realCustomers[i].getCustomerID();%>"
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