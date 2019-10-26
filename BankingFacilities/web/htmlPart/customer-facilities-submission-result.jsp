<%@ page import="persistence.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="persistence.LegalCustomer" %>
<%@ page import="persistence.RealCustomer" %>
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
        <a id="homeLink" href="home.jsp"><b>سامانه مدیریت بانک</b></a>
    </div>
    <div id="middleBar">
        <div id="rightBar">
            <div id="verticalLinks">
                <ul id="vertLinks">
                    <a class="vertLink" href="http://localhost:8080/htmlPart/new-user.jsp">تعریف مشتری جدید</a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/search-user.jsp">جتسجو </a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/banking-facilities.jsp">تسهیلات بانکی</a>
                </ul>
            </div>
        </div>
        <%request.setCharacterEncoding("utf8");%>
        <div id="leftBar">
            <div id="table">
                <%!RealCustomer customer;%>
                <%customer=(RealCustomer)request.getAttribute("customer");%>
                <%if((Boolean)request.getAttribute("IsValidFacility")){%>
                <p>پرونده ی تسهیلاتی با موفقیت ثبت گردید
                <%}else{%>
                <p>خطا در ثبت پرونده ی تسهیلاتی!
                <%}%>
                مشخصات مشتری:  <%customer.getFirstName();%> <%customer.getLastName();%> <%customer.getCustomerID();%></p>

            </div>
        </div>
    </div>
</div>
</body>
</html>