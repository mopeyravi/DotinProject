<%@ page import="java.io.*,java.util.*" %>
<%@ page import="persistence.GrantCondition" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<title>Banking Facilities</title>
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
<!-- Styles for my specific scrolling content -->
<style type="text/css"></style>
<div id="mainBar">
    <div id="topBar">
        <br>
        <a id="homeLink" href="home.jsp"><b>Banking Facilities</b></a>
    </div>
    <div id="middleBar">
        <div id="rightBar">
            <div id="verticalLinks">
                <ul id="vertLinks">
                    <a class="vertLink" href="http://localhost:8080/htmlPart/new-user.jsp">Create New User</a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/search-user.jsp">Search </a>
                    <a class="vertLink" href="http://localhost:8080/htmlPart/banking-facilities.jsp">Banking
                        Facilities</a>
                </ul>
            </div>
        </div>
        <div id="leftBar">
            <div id="table">
                <%!ArrayList<GrantCondition> grantConditions;%>
                <%grantConditions = new ArrayList<GrantCondition>();%>
                <%request.getSession().setAttribute("type", request.getParameter("typeName"));%>
                <%request.getSession().setAttribute("interest", request.getParameter("interestRate"));%>

                <form action="grant-condition.jsp" align="center">
                    <div id="ConditionDefinition">

                        <ul>Name: &ensp;<input type="text" name="Name" required="true"></ul>
                        <ul>Minimum Amount: &ensp;<input type="text" name="MinimumAmount" required="true">&ensp;&ensp;Maximum Amount:&ensp;<input
                                type="text" name="MaximumAmount" required="true"></ul>
                        <ul>Minimum Time:&ensp;&ensp;&ensp;&ensp;<input type="text" name="MinimumTime" required="true">&ensp;&ensp;Maximum
                            Time:&ensp;&ensp;&ensp;<input type="text" name="MaximumTime" required="true"></ul>
                        <ul>
                            <button type="Add" name="AddCondition" value="Add">Add</button>
                        </ul>

                    </div>
                    <ul>Type Name: <input type="text" size="5" name="typeName"
                                          value="<%=request.getParameter("typeName")%>" readonly>
                        Interest rate: <input type="text" size="5" name="interestRate"
                                              value="<%=request.getParameter("interestRate")%>" readonly>
                    </ul>
                </form>

                <ul>
                    <table id="searchTable">

                        <%if ((((Map) request.getParameterMap()).containsKey("MinimumTime"))) {%>
                        <tbody>
                        <tr class="rows" id="ID0">
                            <th>Name</th>
                            <th>Minimum Amount</th>
                            <th>Maximum Amount< /th>
                            <th>Minimum Time</th>
                            <th>Maximum Time</th>
                        </tr>
                        </tbody>

                        <%!Enumeration attributeNames;%>
                        <%attributeNames = request.getSession().getAttributeNames();%>
                        <%
                            while (attributeNames.hasMoreElements()) {
                                String attrName = (String) attributeNames.nextElement();
                                if (attrName.equals("grantConditions")) {
                                    grantConditions = (ArrayList<GrantCondition>) request.getSession().getAttribute("grantConditions");
                                }
                            }
                        %>

                        <%!GrantCondition grantCondition;%>
                        <%!Map<String, String[]> parameterMap;%>
                        <%parameterMap = ((Map) request.getParameterMap());%>
                        <%
                            grantCondition = new GrantCondition((String) parameterMap.get("Name")[0], new Long(parameterMap.get("MinimumAmount")[0]), new Long(parameterMap.get("MaximumAmount")[0]), Integer.valueOf(parameterMap.get("MinimumTime")[0]).intValue(), Integer.valueOf(parameterMap.get("MaximumTime")[0]).intValue());%>
                        <%grantConditions.add(grantCondition);%>
                        <%request.getSession().setAttribute("grantConditions", grantConditions);%>
                        <%!int i;%>
                        <%for (i = 0; i < grantConditions.size(); i++) {%>
                        <tbody>
                        <tr class="rows">
                            <td><input type="text" size="5" name="Name"
                                       value="<%=grantConditions.get(i).getName()%>" readonly></td>
                            <td><input type="text" size="6" name="minimumAmount"
                                       value="<%=grantConditions.get(i).getMinAgreementAmount()%>" readonly></td>
                            <td><input type="text" size="5" name="maximumAmount"
                                       value="<%=grantConditions.get(i).getMaxAgreementAmount()%>" readonly></td>
                            <td><input type="text" size="6" name="minimumTime"
                                       value=<%=grantConditions.get(i).getMinAgreementTime()%> readonly></td>
                            <td><input type="text" size="5" name="maximumTime"
                                       value="<%=grantConditions.get(i).getMaxAgreementTime()%>" readonly></td>
                        </tr>
                        </tbody>
                        <%}%>
                        <%}%>

                    </table>
                </ul>
                <form action="http://localhost:8080/DefineNewFacilitiesServlet" align="center">
                    <ul>
                        <button type="submit" name="submitFacilities" value="submit">Submit</button>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>