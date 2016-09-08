<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.yixueserver.po.*"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String teacher_name = (String)session.getAttribute("teacher_name");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息</title>
<style type="text/css">
.tdStyle1{
	background-color:#ffdab9;
	width:250px;
	height:50px;
	font-size:18px;
}
.tdStyle2{
	width: 250px;
	height: 40px;
}
</style>

</head>
<body>
	<%
	if(teacher_name==null){ %>
	<p align="center">请先登录后再进行操作，正在为您跳转...</p>
	<%		response.setHeader("refresh", "1;URL=login.jsp");
	}else{ %>
	<div align="right" style="width:100%;height:100px">
		<%=teacher_name %>老师，欢迎您
		<a href="exit.jsp" style="text-decoration:none">退出</a>
	</div>
	<center>
		<table>
			<tr>
				<td align="center" class="tdStyle1">课程名</td>
				<td align="center" class="tdStyle1">上课地点</td>
				<td align="center" class="tdStyle1">上课时间</td>
				<td align="center" class="tdStyle1">操作</td>
			</tr>

			<c:forEach var="info" items="${infos}">
				<tr>
					<td align="center" class="tdStyle2">${info.course_name}</td>
					<td align="center" class="tdStyle2">${info.classroom}</td>
					<td align="center" class="tdStyle2">${info.course_time}</td>
					<td align="center" class="tdStyle2">
						<a href="QueryQuestionServlet?course_number=${info.course_number}&class_number=${info.class_number}&course_name=${info.course_name}" style="text-decoration: none">进入课堂</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<%	}
%>
</body>
</html>