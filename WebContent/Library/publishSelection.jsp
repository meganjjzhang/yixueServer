<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yixueserver.po.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	Selection_Bean selection = (Selection_Bean)request.getAttribute("selection");
	int minutes = 0;
	int seconds = 0;
	try{
		minutes = Integer.parseInt((String)request.getAttribute("minutes"));
	} catch(NumberFormatException e){
		e.printStackTrace();
	}
	try{
		seconds = Integer.parseInt((String)request.getAttribute("seconds"));
	} catch(NumberFormatException e){
		e.printStackTrace();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示发布问题</title>
<link href="/yixueServer/css/tdStyle.css" rel="stylesheet" type="text/css"/>
<link href="/yixueServer/css/buttonStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	function clock(){
 		var min = document.getElementById("endTime_min");
		var sec = document.getElementById("endTime_sec");
		if(sec.innerHTML<=0){
			if(min.innerHTML<=0){
				window.location.href = "FinishSelectionServlet?s_id=<%=selection.getS_id()%>";
			}else{
				min.innerHTML = min.innerHTML - 1;
				sec.innerHTML = 59;
				setTimeout("clock()",1000);
 			}
		}else{
			sec.innerHTML = sec.innerHTML - 1;
			setTimeout("clock()",1000);
		}
	}
	setTimeout("clock()",1000);
</script>
</head>

<body>
	<div align="right">
		<a href="QueryAllSelectionServlet" style="text-decoration:none">功能主页</a>
	</div>
    <br><p>
		剩余时间：<span id="endTime_min"><%=minutes %></span>:<span id="endTime_sec"><%=seconds %></span>
    <br>
    
    <h2 align="center">答题展示</h2>
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
				<td class="tdStyle2"><span id="cor" style="display: none;color: #f00;"><%=selection.getCorrectChoice() %></span><a id="con" href="javascript:show()" style="text-decoration:none">显示</a></td>
				</tr>
			<tr>
				<td class="tdStyle1">答题时间</td>
				<td class="tdStyle2"><%=selection.getMinutes() %>分钟</td>
				</tr>
			<tr>
				<td class="tdStyle1">分&nbsp;&nbsp;&nbsp;&nbsp;值</td>
				<td class="tdStyle2"><%=selection.getScore() %>分</td>
				</tr>
			<tr>
				<td></td>
				<td>
					<form action="FinishSelectionServlet" method="post" onsubmit="return stop()">
						<input type="hidden" name="s_id" value="<%=selection.getS_id()%>">
						<input type="submit" value="停止答题" title="点击停止答题" class="buttonStyle">
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
		var cho = confirm("现在要公布正确答案吗？");
		if(cho){
			cor.style.display = "block";
			con.style.display = "none";
		}
	}

	function stop(){
		var jud = confirm("确定要停止答题吗?");
		return jud;
	}
</script>
</html>