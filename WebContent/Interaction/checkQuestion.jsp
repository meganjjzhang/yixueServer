<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String theme = request.getParameter("theme");
	String content = request.getParameter("content");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示问题</title>
<link href="/yixueServer/css/tdStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div align="right">
		<a href="/yixueServer/QueryQuestionServlet" style="text-decoration:none">功能主页</a>
	</div>
	<h2 align="center">查看问题详情</h2>
	<hr align="center" width="90%"><br>
	<div align="center">
		<table border="0">
			<tr>
				<td class="tdStyle1" align="center">主题</td>
				<td class="tdStyle2" ><%=theme%></td>
			</tr>
			<tr>
				<td class="tdStyle1" align="center">内容</td>
				<td class="tdStyle2"><%=content %></td>
			</tr>
		</table>
	</div>
	
</body>
</html>