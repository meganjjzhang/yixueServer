<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yixueserver.po.Selection_Bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	Selection_Bean selection = (Selection_Bean)request.getAttribute("selection");
// 	int s_id = 0;
// 	try{
// 		s_id = Integer.parseInt(request.getParameter("s_id"));
// 	} catch(NumberFormatException e){
// 		e.printStackTrace();
// 		s_id = 0;
// 	}
// 	String theme = request.getParameter("theme");
// 	String content = request.getParameter("content");
// 	String A = request.getParameter("A");
// 	String B = request.getParameter("B");
// 	String C = request.getParameter("C");
// 	String D = request.getParameter("D");
// 	String correctChoice = request.getParameter("correctChoice");
// 	int score = 1;
// 	try {
// 		score = Integer.parseInt(request.getParameter("score"));
// 	} catch (NumberFormatException e) {
// 		e.printStackTrace();
// 		score = 1;
// 	}
// 	int minutes = 45;
// 	try {
// 		minutes = Integer.parseInt(request.getParameter("minutes"));
// 	} catch (NumberFormatException e) {
// 		e.printStackTrace();
// 		minutes = 45;
// 	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看问题</title>
<link href="/yixueServer/css/tdStyle.css" rel="stylesheet" type="text/css"/>
<link href="/yixueServer/css/buttonStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div align="right">
		<a href="/yixueServer/QueryAllSelectionServlet" style="text-decoration:none">功能主页</a>
	</div>
	<h2 align="center">查看问题</h2>
	<hr align="center" width="90%">
	<div align="center">
		<table border="0">
			<tr>
				<td class="tdStyle1">主&nbsp;&nbsp;&nbsp;&nbsp;题</td>
				<td class="tdStyle2"><%=selection.getTheme() %></td>
				</tr>
			<tr>
				<td class="tdStyle1">内&nbsp;&nbsp;&nbsp;&nbsp;容</td>
				<td class="tdStyle2"><%=selection.getContent() %></td>
				</tr>
			<tr>
				<td rowspan="4" class="tdStyle1">选&nbsp;&nbsp;&nbsp;&nbsp;项</td>
				<td class="tdStyle2">A.<%=selection.getA() %></td>
				</tr>
			<tr>
				<td class="tdStyle2">B.<%=selection.getB() %></td>
				</tr>
			<tr>
				<td class="tdStyle2">C.<%=selection.getC() %></td>
				</tr>
			<tr>
				<td class="tdStyle2">D.<%=selection.getD() %></td>
				</tr>
			<tr>
				<td class="tdStyle1">正确选项</td>
				<td class="tdStyle2"><span id="cor" style="display: none;"><%=selection.getCorrectChoice() %></span><a id="con" href="javascript:show()" style="text-decoration:none">显示</a></td>
				</tr>
			<tr>
				<td class="tdStyle1">答题时间</td>
				<td class="tdStyle2"><%=selection.getMinutes() %></td>
				</tr>
			<tr>
				<td class="tdStyle1">分&nbsp;&nbsp;&nbsp;&nbsp;值</td>
				<td class="tdStyle2"><%=selection.getScore() %></td>
				</tr>
			<tr>
				<td></td>
				<td>
					<form action="alterSelection.jsp" method="post">
						<input type="hidden" name="s_id" value="<%=selection.getS_id()%>"/>
						<input type="hidden" name="theme" value="<%=selection.getTheme()%>"/>
						<input type="hidden" name="content" value="<%=selection.getContent()%>"/>
						<input type="hidden" name="A" value="<%=selection.getA()%>"/>
						<input type="hidden" name="B" value="<%=selection.getB()%>"/>
						<input type="hidden" name="C" value="<%=selection.getC()%>"/>
						<input type="hidden" name="D" value="<%=selection.getD()%>"/>
						<input type="hidden" name="correctChoice" value="<%=selection.getCorrectChoice()%>"/>
						<input type="hidden" name="minutes" value="<%=selection.getMinutes()%>分钟"/>
						<input type="hidden" name="score" value="<%=selection.getScore()%>分"/>
						<input type="submit" value="修改" class="buttonStyle" title="点击修改问题"/>
						<input type="button" value="发布" class="buttonStyle" onclick="javascript:publish()" title="点击发布问题"/>
					</form>
					</td>
				</tr>
		</table>
	</div>
	
</body>
<script type="text/javascript">
	function show(){
		var cor = document.getElementById("cor");
		var con = document.getElementById("con");
		if(con.innerHTML=="显示"){
			cor.style.display = "block";
			con.innerHTML = "隐藏";
		}else{
			cor.style.display = "none";
			con.innerHTML = "显示";
		}
	}
	function publish(){
		var cho = confirm("确定要发布当前问题吗？");
		if(cho){
			var url = "/yixueServer/JudgeSelectionServlet?s_id=<%=selection.getS_id()%>";
 			window.location.href = url;
		}
	}
</script>
</html>