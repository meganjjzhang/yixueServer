<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String teacher_number = (String)session.getAttribute("teacher_number");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加问题</title>
<link href="/yixueServer/css/buttonStyle.css" rel="styleSheet" type="text/css" />
<link href="/yixueServer/css/inputStyle.css" rel="styleSheet" type="text/css" />
<link href="/yixueServer/css/textareaStyle.css" rel="styleSheet" type="text/css" />
<link href="/yixueServer/css/optionStyle.css" rel="styleSheet" type="text/css" />
</head>
<body>

	<div align="right">
		<a href="/yixueServer/QueryQuestionServlet" style="text-decoration:none">功能主页</a>
	</div>
	<h2 align="center">添加问题</h2>
	<hr align="center" width="90%"><br>

	<form action="/yixueServer/InsertSelectionServlet" method="post" onsubmit="return clink()">
		<table align="center" border="0">
			<tr>
				<td><label for="theme" style="padding: 20px;">主&nbsp;&nbsp;&nbsp;&nbsp;题</label></td>
				<td><input type="text" size="50" name="theme" id="theme" class="inputStyle"></td>
				</tr>
			<tr>
				<td><label for="content" style="padding: 20px;">内&nbsp;&nbsp;&nbsp;&nbsp;容</label></td>
				<td><textarea rows="4" cols="44" name="content" id="content" class="textareaStyle"></textarea></td>
				</tr>
			<tr>
				<td align="right"><label for="A" style="padding: 20px;">A</label></td>
				<td><input type="text" size="50"  name="A" id="A" class="inputStyle"></td>
				</tr>
			<tr>
				<td align="right"><label for="B" style="padding: 20px;">B</label></td>
				<td><input type="text" size="50" name="B" id="B" class="inputStyle"></td>
				</tr>
			<tr>
				<td align="right"><label for="C" style="padding: 20px;">C</label></td>
				<td><input type="text" size="50" name="C" id="C" class="inputStyle"></td>
				</tr>
			<tr>
				<td align="right"><label for="D" style="padding: 20px;">D</label></td>
				<td><input type="text" size="50" name="D" id="D" class="inputStyle"></td>
				</tr>
			<tr>
				<td><label for="correctChoice" style="padding: 20px;">正确选项</label></td>
				<td>
					<select name="correctChoice" id="correctChoice" class="optionStyle">
						<option value="A" selected="selected">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
						</select>
					</td>
				</tr>
			<tr>
				<td><label for="minutes" style="padding: 20px;">答题时间</label></td>
				<td>
					<select name="minutes" id="minutes" class="optionStyle">
						<option value="3">3</option>
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="15">15</option>
						<option value="20">20</option>
						<option value="25">25</option>
						<option value="30">30</option>
						<option value="35">35</option>
						<option value="40">40</option>
						<option value="45" selected="selected">45</option>
						</select>
					分钟</td>
				</tr>
			<tr>
				<td><label for="score" style="padding: 20px;">分&nbsp;&nbsp;&nbsp;&nbsp;值</label></td>
				<td>
					<select name="score" id="score" class="optionStyle">
						<option value="1" selected="selected">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						</select>
					分</td>
				</tr>
			<tr>
				<td></td>
				<td><input type="reset" value="重置" class="buttonStyle">
					<input type="submit" value="确定" class="buttonStyle" title="点击添加问题">
					</td>
				</tr>
		</table>
	</form>
	
</body>
<script type="text/javascript">
function clink(){
	var theme=document.getElementById("theme").value;
	var content=document.getElementById("content").value;
	var A=document.getElementById("A").value;
	var B=document.getElementById("B").value;
	var C=document.getElementById("C").value;
	var D=document.getElementById("D").value;
	if(theme.length==0){
		alert("主题不能为空 ");
		return false;
	}else if(content.length==0){
		alert("内容不能为空 ");
		return false;
	}else if(A.length==0){
		alert("A选项不能为空 ");
		return false;
	}else if(B.length==0){
		alert("B选项不能为空 ");
		return false;
	}else if(C.length==0){
		alert("C选项不能为空 ");
		return false;
	}else if(D.length==0){
		alert("D选项不能为空 ");
		return false;
	}else{
		return true;
	}
}
</script>
</html>