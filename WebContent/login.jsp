<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String teacher_number = (String)session.getAttribute("teacher_number");
	String tag = (String)request.getAttribute("tag");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>


<style type="text/css">
body {
	font-weight: bold;
	font-family: "MS Serif", "New York", serif;
	font-size: 16px;
}

.table-b table tb {
	border: 1px solid #FFF
}
.inputStyle{
	width:250px;
	height:25px
}
.buttonStyle {
	width: 125px;
	height: 30px
}
</style>
<img src="/yixueServer/img/login_background.jpg" width="100%" height="100%" style="position:absolute;top:0;left:0;right:100;bottom:500;z-index:-1">
</head>
<body>
<%
	if(tag!=null && tag.equals("failed")){ //登录失败 
		request.removeAttribute("tag"); %>
		<p align="center">用户名或密码错误，请重新登录...</p>
<%		response.setHeader("refresh", "1;URL=login.jsp");
	}else{
		if(teacher_number!=null){ %>
			<p align="center">登录成功，正在为您跳转...</p>
	<%		response.setHeader("refresh", "1;URL=QueryCourseServlet");
		}else{ %>
			<br>
			<br>
			<br>
			<br>
			<br>
			<center>
				<div class="table-b">
					<form id="login" action="LoginServlet" method="post">
						<table border="0" cellpadding="10" cellspacing="2">
							<tr>
								<td><label for="teacher_number">教工号</label></td>
								<td><input type="text" class="inputStyle" id="teacher_number" name="teacher_number"><br></td>
							</tr>
							<tr>
								<td><label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label></td>
								<td><input type="password" class="inputStyle" id="password" name="password"></td>
							</tr>
							<tr><td></td>
								<td><input type="reset" value="重置" class="buttonStyle">
									<input type="button" value="登录" onclick="check()" class="buttonStyle"/></td>
							</tr>
						</table>
					</form>
				</div>
			</center>
	<%	}
	}
%>	
</body>
<script type="text/javascript">
	function check(){
		var tea = document.getElementById("teacher_number").value;
		var pas = document.getElementById("password").value;
		if(tea.length==0){
			alert("教工号不能为空");
		}else if(pas.length==0){
			alert("密码不能为空");
		}else{
			var url = "LoginServlet?teacher_number="+tea+"&password="+pas;
			window.location.href = url;
		//	document.getElementById("login").onsubmit = true;
		}
	}
</script>
</html>