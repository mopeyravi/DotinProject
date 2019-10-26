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
                <%!RealCustomer customer;%>
                <%customer=(RealCustomer)request.getAttribute("customer");%>
                <%if((Boolean)request.getAttribute("IsValidFacility")){%>
                <p>The Facilities Is Submitted Successfully
                <%}else{%>}
                <p>The Facilities Is Submitted UnSuccessfully
                <%}%>
                For Customer: <%customer.getFirstName();%> <%customer.getLastName();%> <%customer.getCustomerID();%></p>

            </div>
        </div>
    </div>
</div>
</body>
</html>