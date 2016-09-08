<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	session.removeAttribute("teacher_number");
	session.removeAttribute("teacher_name");
	session.removeAttribute("course_number");
	session.removeAttribute("class_number");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退出</title>
</head>
<body>
	<p align="center">退出成功...</p>
	<%
		response.setHeader("refresh", "1;URL=index.jsp");
	%>
</body>
</html>