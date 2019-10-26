<%@ page import="java.io.*,java.util.*" %>
<%@ page import="persistence.GrantCondition" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="content-type" content="text/html; charset=UTF-8" /></head>
<body>
<title>سامانه مدیریت بانک</title>
<!--<link rel="Stylesheet" type="text/css" href="style/smoothDivScroll.css" /> -->
<link rel="Stylesheet" type="text/css" href="style/stylemain.css">
<link rel="Stylesheet" type="text/css" href="style/style.css">
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
<script>
    function addNewRow() {
//        alert(""+document.getElementById("nameField").value+"\n"+document.getElementById("MaximumAmountField").value+"\n"+document.getElementById("MinimumAmountField").value+"\n"+document.getElementById("MaximumTimeField").value+"\n"+document.getElementById("MinimumTimeField").value);

        var table = document.getElementById("searchTable");
        var newRow = table.insertRow(-1);
        var nameCell = newRow.insertCell(0);
        var maximumAmountCell = newRow.insertCell(1);
        var minimumAmountCell = newRow.insertCell(2);
        var maximumTimeCell = newRow.insertCell(3);
        var minimumTimeCell = newRow.insertCell(4);
        nameCell.innerHTML = "<input type=\"text\"  name=\"Name_table\" value=\""+document.getElementById("nameField").value+"\"\>";
        maximumAmountCell.innerHTML = "<input type=\"text\"  name=\"MaximumAmount_table\" value=\""+document.getElementById("MaximumAmountField").value+"\"\>";
        minimumAmountCell.innerHTML = "<input type=\"text\"  name=\"MinimumAmount_table\" value=\""+document.getElementById("MinimumAmountField").value+"\"\>";
        maximumTimeCell.innerHTML = "<input type=\"text\"  name=\"MaximumTime_table\" value=\""+document.getElementById("MaximumTimeField").value+"\"\>";
        minimumTimeCell.innerHTML = "<input type=\"text\"  name=\"MinimumTime_table\" value=\""+document.getElementById("MinimumTimeField").value+"\"\>";
        <%--<%GrantCondition grantCondition=new GrantCondition(document.getElementById("nameField").value,(Long)document.getElementById("MaximumAmountField").value,(Long)document.getElementById("MinimumAmountField").value,(int)document.getElementById("MaximumTimeField").value,(int)document.getElementById("MinimumTimeField").value)%>--%>
    }
</script>
<!-- Styles for my specific scrolling content -->
<style type="text/css"></style>
<div id="mainBar">
    <div id="topBar">
        <br>
        <a id="homeLink" href="http://localhost:8080/htmlPart/home.jsp"><b>سامانه مدیریت بانک</b></a>
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
            <div id="table">
                    <div id="ConditionDefinition" align="center">

                        <ul>نام: &ensp;<input type="text" name="Name" id="nameField" required="true"></ul>
                        <ul>حداقل مبلغ قرارداد: &ensp;<input type="text" name="MinimumAmount" id="MinimumAmountField" required="true">&ensp;&ensp;حداکثر مبلغ قرارداد:&ensp;<input
                                    type="text" name="MaximumAmount" id="MaximumAmountField" required="true"></ul>
                        <ul>حداقل مدت قرارداد:&ensp;&ensp;&ensp;&ensp;<input type="text" name="MinimumTime" id="MinimumTimeField" required="true">&ensp;&ensp;حداکثر مدت قرارداد:&ensp;&ensp;&ensp;<input type="text" name="MaximumTime" id="MaximumTimeField" required="true"></ul>
                        <ul>
                            <button type="Add" name="AddCondition" value="Add" onclick="addNewRow()">Add</button>
                        </ul>

                    </div>

                <ul>

                </ul>
                <%request.setCharacterEncoding("UTF-8");%>
                <form action="http://localhost:8080/DefineNewFacilitiesServlet" align="center">
                    <ul>نام نوع تسهیلات: <input type="text" size="5" name="typeName" value="<%=java.net.URLDecoder.decode(request.getParameter("typeName"),"UTF-8")%>" readonly>
                        نرخ سود: <input type="text" size="5" name="interestRate" value="<%=request.getParameter("interestRate")%>" readonly>
                    </ul>
                    <table id="searchTable">
                        <tbody>
                        <tr class="rows" id="ID0">
                            <th>نام</th>
                            <th>حداقل مبلغ</th>
                            <th>حداکثر مبلغ </th>
                            <th> حداقل زمان</th>
                            <th> حداکثر زمان</th>
                        </tr>
                        </tbody>

                    </table>
                    <ul>
                        <button type="submit" name="submitFacilities" value="ثبت">ثبت</button>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>