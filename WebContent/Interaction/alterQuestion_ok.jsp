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
<title>修改问题结果</title>
</head>
<body>

<%
	if(tag==null){ //异常情况  %>
		<div align="center">
			修改问题出错，返回<a href="/yixueServer/QueryQuestionServlet">功能主页</a>
		</div>
<%	}else if(tag.equals("succeeded")){ //修改成功  %>
		<div align="center">
			修改成功，正在为您跳转到<a href="/yixueServer/QueryQuestionServlet">功能主页</a>
		</div>
<%		response.setHeader("refresh", "2;URL=/yixueServer/QuerySelectionServlet");
	}else{ //修改失败  %>
		<div align="center">
			修改失败，返回<a href="/yixueServer/QueryQuestionServlet">功能主页</a>
		</div>
<%	}
%>

</body>
</html>