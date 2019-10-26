<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>سامانه مدیریت بانک</title>
    <!--<link rel="Stylesheet" type="text/css" href="style/smoothDivScroll.css" /> -->
    <link rel="Stylesheet" type="text/css" href="style/stylemain.css"/>
    <link rel="Stylesheet" type="text/css" href="style/style.css"/>

    <script src="js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
        var req = new XMLHttpRequest();
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
        function validateRealEntries() {
            var nationalCode = document.getElementsByName("nationalcode")[0].value;
            var i;
            var sumNationalCode = 0;
            var text = "";
            var a;
            var flag=0;
            var pos;
            for (i = 0; !(i > nationalCode.length - 2); i++) {

                pos=i+1;
                text+=" "+pos;

                a = parseInt(nationalCode.substring(i, i + 1));
                text+="*"+a;
                sumNationalCode += a * pos;
            }
            if (nationalCode.length != 10) {
                flag=1;
                text+="National Code length must be 10 not " + nationalCode.length + "!\n";
            }
            if (!(((sumNationalCode % 11) == parseInt(nationalCode.substring(nationalCode.length - 1, nationalCode.length))) || ((sumNationalCode % 11) == 11 - parseInt(nationalCode.substring(nationalCode.length - 1, nationalCode.length))))) {
                flag=1;
                text+="Invalid NationalCode!\n";
            }
            if(flag==1){
                alert(text);
                return false;
            }
            else{
                document.getElementById("newRealUser").action="http://localhost:8080/CreateNewRealCustomerServlet";
                document.getElementById("newRealUser").action.submit();
            }
        }
    </script>
    <style type="text/css"></style>
</head>

<body>
<div id="mainBar">
    <div id="topBar">
        <br>
        <a id="homeLink" href="Home.html"><b>سامانه مدیریت بانک</b></a>
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

            <ul class="tabs">
                <li><a href="#tab1">مشتری حقیقی</a></li>
                <li><a href="#tab2">مشتری حقوقی</a></li>
            </ul>
            <%request.setCharacterEncoding("utf8");%>
            <div class="tab_container">
                <div id="tab1" class="tab_content">
                    <form align=center id="newRealUser">
                        <ul>نام:&ensp;&ensp; &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input type="text" name="firstname" required="true"></ul>
                        <ul>نام خانوادگی: &ensp;<input type="text" name="lastname" required="true"></ul>
                        <ul>نام پدر: &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input type="text" name="fathername" required="true"></ul>
                        <ul>تاریخ تولد:&ensp;&ensp;&ensp;&ensp;<input type="text" name="birthdate" required="true"></ul>
                        <ul>کد ملی: &ensp;&ensp;&ensp;&ensp;<input type="text" name="nationalcode" required="true"></ul>
                        <br>
                        <ul><input type="submit" id="newRealUserSubmit" value="ثبت" onclick="validateRealEntries();"></ul>
                    </form>
                </div>
                <div id="tab2" class="tab_content">
                    <form  align=center action="http://localhost:8080/CreateNewLegalCustomerServlet" >
                        <ul>نام شرکت:&ensp;&ensp;&ensp;<input type="text" name="companyname" required="true"></ul>
                        <ul>کد اقتصادی: &ensp;<input type="text" name="economiccode" required="true"></ul>
                        <ul>تاریخ ثبت:&ensp;&ensp;&ensp; <input type="text" name="submissiondate" required="true"></ul>
                        <br>
                        <ul><input type="submit" value="ثبت"></ul>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
