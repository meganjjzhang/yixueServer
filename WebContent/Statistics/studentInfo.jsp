<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page errorPage="errorPage.jsp" %> --%>
<%@ page import="java.util.*" %>
<%@ page import="com.yixueserver.po.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String student_number = request.getParameter("student_number");
	String course_number = (String)session.getAttribute("course_number");
	String class_number = (String)session.getAttribute("class_number");
	String course_name = (String)session.getAttribute("course_name");
	String student_name = request.getParameter("student_name");
	String totalScore = (String)request.getAttribute("totalScore");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示学生信息</title>
<style type="text/css">
#head{height: 100px;padding-top:10px; padding-right: 10px;background: #ccc}
#main{width: 90%;min-height: 400px;margin:0 auto;background: #ddd;}
.th1{width:100px;height:50px;background:#ccc}
.th2{width:200px;height:50px;background:#ccc}
.td1{width:100px;min-height: 50px;}
.td2{width:200px;min-height: 50px;}
</style>
</head>
<body>
	<div id="head" align="right">
		学生姓名:<%=student_name == null ? "无" : student_name%>&nbsp;&nbsp;总分:<%=totalScore == null ? 0 : totalScore%>
	</div>

	<div id="main" align="center" style="width: 90%;">
		<%
			List<Record_Bean> records = (List<Record_Bean>) request.getAttribute("records");
			if (records == null || records.equals("null")) {
		%>
				<p align="center">暂无相关记录</p>
		<%	} else {
				int pageNum = 1;
				try {
						pageNum = Integer.parseInt((String) request.getAttribute("pageNum"));
				} catch (NumberFormatException e) {
					pageNum = 1;
				}
				int totalPage = 1;
				try {
					totalPage = Integer.parseInt((String) request.getAttribute("totalPage"));
				} catch (NumberFormatException e) {
					totalPage = 1;
				}
		%>
			<table border="0">
				<tr>
					<th class="th2">主题</th>
					<th class="th2">内容</th>
					<th class="th2">选项</th>
					<th class="th2">正确选项</th>
					<th class="th1">我的选项</th>
					<th class="th1">分值</th>
					<th class="th1">得分</th>
					<th class="th1">情况</th>
				</tr>
				<%
					Record_Bean bean = null;
					String temp = "";
					int startPos = (pageNum-1)*10;
					for(int i=startPos;i<records.size()&&i<startPos+10;i++){ 
						bean = records.get(i); %>
						<tr>
							<td class="td2"><%=bean.getTheme() %></td>
							<td class="td2"><%=bean.getContent() %></td>
							<%
								temp = bean.getChoice();
								System.out.println(temp);
								temp = temp.replace("\\n", "<br>");
								System.out.println(temp);
							%>
							<td class="td2"><%=temp %></td>
							<td class="td2" align="center"><%=bean.getCorrectChoice() %></td>
							<td class="td1" align="center"><%=bean.getMyChoice() %></td>
							<td class="td1" align="center"><%=bean.getScore() %></td>
							<td class="td1" align="center"><%=bean.getMyScore() %></td>
							<%
								if(bean.getStatus()==0){ %>
									<td class="td1" align="center">正在答题中</td>
							<%	}else if(bean.getStatus()==1){ %>
									<td class="td1" align="center">已答</td>
							<%	}else{ %>
									<td class="td1" align="center">未答</td>
							<%	}
							%>
						</tr>
				<%	}
				%>
			</table>
			<br>
			<div align="center">
				<a href="QueryStudentInfoServlet?student_number=<%=student_number%>&student_name=<%=student_name%>&pageNum=1" style="text-decoration: none">
					首页</a>
				<%
					if(pageNum==1){ %>
						上一页
				<%	}else{ %>
						<a href="QueryStudentInfoServlet?student_number=<%=student_number%>&student_name=<%=student_name%>&pageNum=<%=pageNum-1%>">
							上一页</a>
				<%	}
					if(pageNum==totalPage){ %>
						下一页
				<%	}else{ %>
						<a href="QueryStudentInfoServlet?student_number=<%=student_number%>&student_name=<%=student_name%>&pageNum=<%=pageNum+1%>">
							下一页</a>
				<%	}
				%>
				
					<select id="page" onchange="submit()">
						<%
							for(int i=1;i<=totalPage;i++){ %>
								<option value="<%=i %>" <%=(pageNum==i)?"selected":"" %>> 第<%=i %>页</option>
						<%	}
						%>
					</select>
				共<%=totalPage %>页
				<a href="QueryStudentInfoServlet?student_number=<%=student_number%>&student_name=<%=student_name%>&pageNum=<%=totalPage%>" style="text-decoration: none">
					末页</a>
			</div>
		<%
			}
		%>
	</div>
</body>
<script type="text/javascript">
	function submit(){
		var pa = document.getElementById("page").value;
		var url = "QueryStudentInfoServlet?student_number=<%=student_number%>&student_name=<%=student_name%>&pageNum="+pa;
		window.location.href = url;
	}
</script>
</html>