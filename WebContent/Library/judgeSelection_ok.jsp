<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题发布判断</title>
</head>
<body>
	<div align="center">
		已存在发布中问题，请先结束后再重新发布，正在为您跳转到<a href="QueryAllSelectionServlet">功能主页</a>
	</div>
	<%
		response.setHeader("refresh", "2;URL=QueryAllSelectionServlet");
	%>
</body>
</html>