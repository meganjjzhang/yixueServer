<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int a = 1;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除测试</title>
</head>
<body>
<a href="javascript:deleteQuestion(<%=a%>)">删除</a>
<a href="javascript:deleteQuestion(2)">删除</a>
</body>
<script type="text/javascript">
	function deleteQuestion(a){
		window.location.href = "test2.jsp?a=" + a;
	}
</script>
</html>