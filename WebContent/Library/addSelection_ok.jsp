<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String tag = (String)request.getAttribute("tag");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加问题结果</title>
</head>
<body>
<%
	if(tag==null){ //异常情况  %>
		<div align="center">
			添加问题出错，返回<a href="/yixueServer/QueryAllSelectionServlet">功能主页</a>&nbsp;
			<a href="addSelection.jsp">重新添加</a>
		</div>
<%	}else if(tag.equals("succeeded")){ //添加成功  %>
		<div align="center">
			添加成功，正在为您跳转到<a href="/yixueServer/QueryAllSelectionServlet">功能主页</a>
		</div>
<%		response.setHeader("refresh", "2;URL=/yixueServer/QueryAllSelectionServlet");
	}else{ //添加失败  %>
		<div align="center">
			添加失败，返回<a href="/yixueServer/QueryAllSelectionServlet">功能主页</a>
			<a href="addSelection.jsp">重新添加</a>
		</div>
<%	}
%>
</body>
</html>