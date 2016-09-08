<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	int q_id = 0;
	try{
		q_id = Integer.parseInt(request.getParameter("q_id"));
	} catch(NumberFormatException e){
		q_id = 0;
	}
	String theme = request.getParameter("theme");
	String content = request.getParameter("content");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示问题</title>
<link href="/yixueServer/css/buttonStyle.css" rel="stylesheet" type="text/css"/>
<link href="/yixueServer/css/tdStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div align="right">
		<a href="/yixueServer/QueryQuestionServlet" style="text-decoration:none">功能主页</a>
	</div>
	<h2 align="center">问题讨论</h2>
	<hr align="center" width="90%">
	<div align="center">
		<table border="0">
			<tr>
				<td class="tdStyle1" align="center">主题</td>
				<td class="tdStyle2"><%=theme%></td>
			</tr>
			<tr>
				<td class="tdStyle1" align="center">内容</td>
				<td class="tdStyle2"><%=content %></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<form action="/yixueServer/AlterQuestionStatusServlet" method="post">
						<input type="hidden" name="q_id" value="<%=q_id%>">
						<input type="hidden" name="status" value="2">
						<input type="submit" value="结束" title="点击结束问题讨论" class="buttonStyle">
					</form>
				</td>
				</tr>
		</table>
	</div>
	
</body>
</html>