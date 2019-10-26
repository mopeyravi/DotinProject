<%@ page import="java.io.*,java.util.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<title>CustomerAccountAutomation</title>
<!--<link rel="Stylesheet" type="text/css" href="style/smoothDivScroll.css" /> -->
<link rel="Stylesheet" type="text/css" href="style/stylemain.css"> 
  <link rel="Stylesheet" type="text/css" href="style/style.css"> 
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
			   <a class="vertLink" href="http://localhost:8080/htmlPart/BankingFaclilities.html">Banking Facilities</a>
			  </ul> 
			 </div> 
			</div> 
			<div id="leftBar">
				<div id="table">
					<form action="grantCondition.jsp" align="center">
					<div id="ConditionDefinition">
					<ul>Name: &ensp;<input type="text"  name="Name" ></ul>
					<ul>Minimum Amount: &ensp;<input type="text"  name="MinimumAmount " >&ensp;&ensp;Maximum Amount:&ensp;<input type="text"  name="MaximumAmount" ></ul>
					<ul>Minimum Time:&ensp;&ensp;&ensp;&ensp;<input type="text"  name="MinimumTime" >&ensp;&ensp;Maximum Time:&ensp;&ensp;&ensp;<input type="text"  name="maximumTime" ></ul>
					<ul><button type="Add" name="AddCondition" value="Add">Add</button></ul>
					</div>
					<%if(!request.getParameter("Name"))%>
					<ul>
						<table id="searchTable">
							<tbody>
								<tr class="rows" id="ID0">
									<th>Name</th>
									<th>Minimum Amount</th>
									<th>Maximum Amount< /th>
									<th>Minimum Time</th>
									<th>Maximum Time</th>
								</tr>
							</tbody>
							<!--
							<tbody>
								<tr class="rows" id="ID4">
									<td><button type="submit" name="DeleteBtn" value="Delete">Delete Customer</button></td>
									<td><button type="submit" name="EditBtn" value="Edit">Edit Customer</button></td> 
									<td><input type="text" size="5" name="companyName" value="TMBA"></td> 
									<td><input type="text" size="6" name="economicCode" value="345778"></td> 
									<td><input type="text" size="5" name="submissionDate" value="13bahman"></td> 
									<td><input type="text" size="4" name="ID" value="4" readonly></td>
								</tr>
							</tbody>
							-->
							
						</table>
					</ul>
					<ul><button type="submit" name="submitFacilities" value="submit">Submit</button></ul>
						
						
					</form>
				</div> 
			</div>
		</div>
	</div>  
 </body>
</html>