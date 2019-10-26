
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>سامانه مدیریت بانک</title>
    <!--<link rel="Stylesheet" type="text/css" href="style/smoothDivScroll.css" /> -->
    <link rel="Stylesheet" type="text/css" href="http://localhost:8080/htmlPart/style/stylemain.css"/>
    <link rel="Stylesheet" type="text/css" href="http://localhost:8080/htmlPart/style/style.css"/>
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
</head>

<body>
<div id="mainBar">
    <div id="topBar">
        </br>
        <a id="homeLink" href="home.jsp"><b>سامانه مدیریت بانک</b></a>
    </div>
    <div id="middleBar">
        <div id="rightBar">
            <div id="verticalLinks">
                <ul id="vertLinks">
                    <a class="vertLink" href="http://localhost:8080/htmlPart/new-user.jsp">تعریف مشتری جدید</a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/search-user.jsp">جستجو </a>
                    <a id="inactive" class="vertLink" href="http://localhost:8080/htmlPart/banking-facilities.jsp">تسهیلات بانکی</a>
                </ul>

            </div>
        </div>
        <%response.setCharacterEncoding("UTF-8");request.setCharacterEncoding("UTF-8");%>
        <div id="leftBar">
            <form  align=center action="http://localhost:8080/htmlPart/grant-condition.jsp">
                <ul>نام نوع تسهیلات:&ensp;<input type="text" name="typeName" required="true"></ul>
                <ul>نرخ سود: &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input type="text"
                                                                                    name="interestRate" required="true"></ul>
                <ul><input type="submit" value="ثبت"></ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>
