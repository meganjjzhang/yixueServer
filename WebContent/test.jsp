<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	int minutes = 0;
	int seconds = 0;
	try{
		minutes = Integer.parseInt(request.getParameter("min"));
	} catch(NumberFormatException e){
		e.printStackTrace();
	}
	try{
		seconds = Integer.parseInt(request.getParameter("sec"));
	} catch(NumberFormatException e){
		e.printStackTrace();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>倒计时</title>
<script type="text/javascript">
	function clock(){
 		var min = document.getElementById("endTime_min");
		var sec = document.getElementById("endTime_sec");
		if(sec.innerHTML<=0){
			if(min.innerHTML<=0){
				alert("该自动结束了");
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
	<div id="div5" align="center">
		剩余时间：<span id="endTime_min"><%=minutes %></span>:<span id="endTime_sec"><%=seconds %></span>
	</div>
</body>
</html>