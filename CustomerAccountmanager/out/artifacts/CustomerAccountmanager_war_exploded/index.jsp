<%--
  Created by IntelliJ IDEA.
  User: DOTIN SCHOOL 3
  Date: 4/14/2015
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>CustomerAccountAutomation</title>
    <!--<link rel="Stylesheet" type="text/css" href="style/smoothDivScroll.css" /> -->
    <link rel="Stylesheet" type="text/css" href="style/stylemain.css" />
    <link rel="Stylesheet" type="text/css" href="style/style.css" />

    <script src="js/jquery.js" type="text/javascript"></script>
    <!--
    <script src="js/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="js/jquery.smoothDivScroll-1.1-min.js" type="text/javascript"></script>
    -->
    <script type="text/javascript">
        $(document).ready(function() {

            //When page loads...
            $(".tab_content").hide(); //Hide all content
            $("ul.tabs li:first").addClass("active").show(); //Activate first tab
            $(".tab_content:first").show(); //Show first tab content

            //On Click Event
            $("ul.tabs li").click(function() {

                $("ul.tabs li").removeClass("active"); //Remove any "active" class
                $(this).addClass("active"); //Add "active" class to selected tab
                $(".tab_content").hide(); //Hide all tab content

                var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
                $(activeTab).fadeIn(); //Fade in the active ID content
                return false;
            });

        });

    </script>



    <!-- Styles for my specific scrolling content -->
    <style type="text/css"></style>
</head>

<body>
<div id="mainBar">
    <div id="topBar">
        </br>
        <a id="homeLink" href="http://localhost:9999/htmlPart/Home.html"><b>Customer Account Manager </b></a>
    </div>
    <div id="middleBar">
        <div id="rightBar">
            <div id="verticalLinks">
                <ul id="vertLinks">
                    <a class="vertLink" href="newUser.html">Create New User</a>
                    <a class="vertLink" href="searchUser.html">Search </a>
                </ul>

            </div>
        </div>
        <div id="leftBar"  >
            <div id="loginForm">
                <form action="http://localhost:9999/UserWelcomeServlet" align=center>
                    <ul>User  Name:&ensp;&ensp; &ensp;<input type="text" name="userName" ></ul>
                    <ul>Pass Word: &ensp;&ensp;&ensp;<input type="text" name="passWord" ></ul>
                    <ul><input type="submit" value="LogIn"></ul>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>

