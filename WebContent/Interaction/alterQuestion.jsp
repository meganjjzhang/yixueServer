<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	int q_id = 0;
	try{
		q_id = Integer.parseInt(request.getParameter("q_id"));
	} catch(NumberFormatException e){
		e.printStackTrace();
	}
	String theme = request.getParameter("theme");
	String content = request.getParameter("content");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改问题</title>
<link href="/yixueServer/css/inputStyle.css" rel="stylesheet" type="text/css"/>
<link href="/yixueServer/css/textareaStyle.css" rel="stylesheet" type="text/css"/>
<link href="/yixueServer/css/buttonStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div align="right">
		<a href="/yixueServer/QueryQuestionServlet" style="text-decoration:none">功能主页</a>
	</div>
	<h2 align="center">修改问题</h2>
	<hr align="center" width="90%"><br>
	<div align="center">
	<form method="post" action="/yixueServer/AlterQuestionInfoServlet" onsubmit="return judge()">
		<input type="hidden" name="q_id" value="<%=q_id%>">
		<table>
			<tr>
				<td><label for="theme" style="padding: 20px">问题主题</label></td>
				<td><input type="text" id="theme" name="theme" class="inputStyle" value="<%=theme%>"></td>
			</tr>
			<tr>
				<td><label for="content" style="padding: 20px">问题内容</label></td>
				<td><textarea name="content" id="content" class="textareaStyle"><%=content %></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="reset" value="重置" class="buttonStyle">
					<input type="submit" value="确定" class="buttonStyle" title="点击提交修改后的问题">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
<script type="text/javascript">
	function judge(){
		var the = document.getElementById("theme").value;
		var con = document.getElementById("content").value;
		if(the.length==0){
			alert("问题主题不能为空");
			return false;
		}else if(con.length==0){
			alert("问题内容不能为空");
			return false;
		}else{
			return true;
		}
	}
</script>
</html>