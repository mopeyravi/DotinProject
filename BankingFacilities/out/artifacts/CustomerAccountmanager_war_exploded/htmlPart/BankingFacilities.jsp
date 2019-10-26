<%@ page import="persistence.RealCustomer" %>
<%@ page import="jdk.nashorn.internal.ir.RuntimeNode" %>
<%@ page import="java.util.List" %>
<%@ page import="persistence.Facilities" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>CustomerAccountAutomation</title>
    <!--<link rel="Stylesheet" type="text/css" href="style/smoothDivScroll.css" /> -->
    <link rel="Stylesheet" type="text/css" href="style/stylemain.css"/>
    <link rel="Stylesheet" type="text/css" href="style/style.css"/>
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
</head>

<body>
<div id="mainBar">
    <div id="topBar">
        </br>
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

            <ul class="tabs">
                <li><a href="#tab1">Facilities Type</a></li>
                <li><a href="#tab2">Open Facilities File</a></li>
            </ul>

            <div class="tab_container">
                <div id="tab1" class="tab_content">
                    <form action="grantCondition.jsp" align=center>
                        <ul>Facilities Type Name:&ensp;<input type="text" name="typeName"></ul>
                        <ul>Interest Rate: &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input type="text"
                                                                                            name="interestRate"></ul>
                        <ul><input type="submit" value="Submit"></ul>
                    </form>
                </div>
                <div id="tab2" class="tab_content">
                    <!-- Change The Action! #######################################
                    ADD PROPER JSP -->
                    <%if (request.getAttribute("customer").toString().isEmpty()) {%>
                    <form action="/OpenFacilitiesFileServlet" align=center>
                        <ul>Customer ID:&ensp;&ensp;<input type="text" name="customerID"></ul>
                        <ul><input type="submit" value="Submit"></ul>
                    </form>
                    <%} else {%>
                    <%!RealCustomer realCustomer;%>
                    <%!Facilities[] facilities;%>
                    <%!int i;%>
                    <%realCustomer = (RealCustomer) request.getAttribute("customer");%>
                    <%facilities = (Facilities[]) request.getAttribute("facilities");%>
                    <form action="" align=center>
                        <ul>Customer ID:&ensp;&ensp;<input type="text" name="customerID" readonly><%
                            realCustomer.getCustomerID();%></ul>
                        <ul>First Name:&ensp;&ensp;<input type="text" name="firstName" readonly><%
                            realCustomer.getFirstName();%></ul>
                        <ul>Last Name:&ensp;&ensp;<input type="text" name="lastName" readonly><%
                            realCustomer.getLastName();%></ul>
                        <ul>
                            <select name="AvailableFacilities">
                                <%for (i = 0; i < facilities.length; i++) {%>
                                <option value="<%facilities[i].getTypeName();%>"><%
                                    facilities[i].getTypeName();%></option>
                                <%}%>
                            </select>
                        </ul>
                        <ul>Agreement Time:&ensp;&ensp;<input type="text" name="agreementTime" ></ul>
                        <ul>Agreement Amount:&ensp;&ensp;<input type="text" name="agreementAmount" ></ul>
                        <ul><input type="submit" value="Submit"></ul>
                    </form>
                    <%}%>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
