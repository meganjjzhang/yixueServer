<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.yixueserver.po.*" %>
<%@ page import="com.yixueserver.dao.*" %>
<%@ page import="com.yixueserver.util.*" %>
<%-- <%@ page errorPage="errorPage.jsp" %> --%>

	<link rel="stylesheet" type="text/css" href="css/grid.css"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String teacher_number = (String)session.getAttribute("teacher_number");
	String teacher_name = (String)session.getAttribute("teacher_name");
	String course_number = request.getParameter("course_number");
	String class_number = request.getParameter("class_number");
	String course_name = (String)session.getAttribute("course_name");
	int choice = 1;
	try{
		choice = Integer.parseInt(request.getParameter("choice"));
	} catch(NumberFormatException e){
		e.printStackTrace();
		choice = 1;
	}
	if(choice<1 || choice>3){
		choice = 1;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能主页</title>
<style type="text/css">
#th1{width: 350px; height: 50px; background: #77b; color: #f00}
#th2{width: 350px; height: 50px; background: #bbb; color: #fff}
#th3{width: 350px; height: 50px; background: #bbb; color: #fff}
</style>
</head>

<body>

<div class="column grid_4">
	
	<div class="shadow">
	<p>Top of shadow</p></div>
	
		<div class="shadow">
						<div class="lightpanel roundedbottom roundedtop masthead">
			<h1><a class="roundedbottom roundedtop" href="/">House of Cards</a></h1>
			</div>
			 <img class="roundedtop portrait" src="http://static.tumblr.com/70e00abd489e8fb06ac4d711fd9c9083/e4rt9ed/rSgnjbdpn/tumblr_static_5z0gpof16q044cos0kgw8gkws.png" />

			<div class="lightpanel">
			<div><p>All things House of Cards.<br />
I take requests for gifs so if there is a particular scene you would like to see giffed, drop me an ask.</p></div>
			</div>





			<div class="lightpanel nav">
			<a class="nav" href="/">Home</a>
			</div>



			<div class="lightpanel nav">
			<a class="nav" href="/archive">Archive</a>
			</div>
			
			 
			<div class="lightpanel nav">
			<a class="nav" href="/ask">Ask me anything</a>
			</div>			
			
			
			
			<div class="lightpanel nav">
			<a class="nav" href="/submit">Submit</a>
			</div>						
			

			
			

			
			
			
			
			<div class="lightpanel nav">
			<a class="nav" href="/tag-directory">Tag Directory</a>
			</div>
			

			

			

			

			
			<!-- Please do not remove credit. Thanks :) -->
			<div class="bottompanel roundedbottom">
			<p><a href="http://www.tumblr.com/theme/7285/" target="_blank">Catching Elephant</a> is a theme by <a href="http://www.catchingzebra.com/" target="_blank">Andy Taylor</a></p>
			</div>
			
		</div>

			
			
			
	</div>

	<div class="column grid_1">
	<p>&nbsp;</p>
	</div>

	<div class="column grid_7">

	
	
	
	
		
			

			</div>
			</div>

	

</body>
<script type="text/javascript">
	function di1() {
		with(document){
			getElementById("div1").style.display = "block";
			getElementById("div2").style.display = "none";
			getElementById("div3").style.display = "none";
			
			getElementById("th1").style.background = "#77b";
			getElementById("th2").style.background = "#bbb";
			getElementById("th3").style.background = "#bbb";
			
			getElementById("interaction").style.color = "#fff";
			getElementById("library").style.color = "#000";
			getElementById("statistics").style.color = "#000";
		}
	}
	function di2() {
		with(document){
			getElementById("div1").style.display = "none";
			getElementById("div2").style.display = "block";
			getElementById("div3").style.display = "none";
			
			getElementById("th1").style.background = "#bbb";
			getElementById("th2").style.background = "#77b";
			getElementById("th3").style.background = "#bbb";
			
			getElementById("interaction").style.color = "#000";
			getElementById("library").style.color = "#fff";
			getElementById("statistics").style.color = "#000";
		}
	}
	function di3() {
		with(document){
			getElementById("div1").style.display = "none";
			getElementById("div2").style.display = "none";
			getElementById("div3").style.display = "block";
			
			getElementById("th1").style.background = "#bbb";
			getElementById("th2").style.background = "#bbb";
			getElementById("th3").style.background = "#77b";
			
			getElementById("interaction").style.color = "#000";
			getElementById("library").style.color = "#000";
			getElementById("statistics").style.color = "#fff";
		}
	}
</script>

<script type="text/javascript">
	function deleteQuestion(theme,q_id){
		var del = confirm('确定删除“'+theme+'”吗？');
		if(del){
			var url = "DeleteQuestionServlet?q_id=" + q_id;
			window.location.href = url;
		}
	}
	function deleteSelection(theme,s_id){
		var del = confirm('确定删除“'+theme+'”吗？');
		if(del){
			var url = "DeleteSelectionServlet?s_id=" + s_id;
			window.location.href = url;
		}
	}
	function publishSelection(theme,s_id,s_pageNum){
		var del = confirm('确定发布“'+theme+'”吗？');
		if(del){
			var url = "JudgeSelectionServlet?s_id=" + s_id + "&s_pageNum=" + s_pageNum;
			window.location.href = url;
		}
	}
</script>

<script type="text/javascript">
	function questionPageChange(){
		var pa = document.getElementById("questionPage").value;
		var url = "QueryQuestionServlet?course_number=<%=course_number%>&class_number=<%=class_number%>&q_pageNum=" + pa;
		window.location.href = url;
	}
	function selectionPageChange(){
		var pa = document.getElementById("selectionPage").value;
		var url = "QueryAllSelectionServlet?course_number=<%=course_number%>&class_number=<%=class_number%>&s_pageNum=" + pa + "&choice=2";
		window.location.href = url;
	}
</script>
</html>