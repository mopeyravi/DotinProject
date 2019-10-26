<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>CustomerAccountAutomation</title>
    <!--<link rel="Stylesheet" type="text/css" href="style/smoothDivScroll.css" /> -->
    <link rel="Stylesheet" type="text/css" href="style/stylemain.css"/>
    <link rel="Stylesheet" type="text/css" href="style/style.css"/>

    <script src="js/jquery.js" type="text/javascript"></script>
    <!--
    <script src="js/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="js/jquery.smoothDivScroll-1.1-min.js" type="text/javascript"></script>
    -->
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
        <a id="homeLink" href="Home.jsp"><b>Customer Account Manager </b></a>
    </div>
    <div id="middleBar">
        <div id="rightBar">
            <div id="verticalLinks">
                <ul id="vertLinks">
                    <a class="vertLink" href="newUser.jsp">Create New User</a>
                    <a class="vertLink" href="searchUser.jsp">Search </a>
                    <a class="vertLink" href="grantCondition.jsp">Banking Facilities</a>
                </ul>

            </div>
        </div>
        <div id="leftBar">
            <div id="loginForm">
                <p>Welcome to Customer Account Manager Automation.</p>

                <p>In order to add new user choose Create New User, from the right bar. You can search previous users by
                    the Search link from right bar. Then you can edit search results. e.g, remove or edit
                    information</p>
            </div>
        </div>

    </div>
</div>
</body>
</html>