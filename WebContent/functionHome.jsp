<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.yixueserver.po.*" %>
<%@ page import="com.yixueserver.dao.*" %>
<%@ page import="com.yixueserver.util.*" %>
<%-- <%@ page errorPage="errorPage.jsp" %> --%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String teacher_number = (String)session.getAttribute("teacher_number");
	String teacher_name = (String)session.getAttribute("teacher_name");
	String course_number = request.getParameter("course_number");
	String class_number = request.getParameter("class_number");
	String course_name = (String)session.getAttribute("course_name");
	int choice = 1;
	try{
		choice = Integer.parseInt(request.getParameter("choice"));
	} catch(NumberFormatException e){
		e.printStackTrace();
		choice = 1;
	}
	if(choice<1 || choice>3){
		choice = 1;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能主页</title>
<style type="text/css">
#th1{width: 350px; height: 50px; background: #77b; color: #f00}
#th2{width: 350px; height: 50px; background: #bbb; color: #fff}
#th3{width: 350px; height: 50px; background: #bbb; color: #fff}
</style>
</head>

<body>
	<%
		if(teacher_number==null){ //session已过期  %>
			<p align="center">登录超期，请重新登录</p>
		<%	response.setHeader("refresh","1;URL=login.jsp");
		}else{ %>
			<div align="right" style="width:100%;height:100px">
				<%=teacher_name %>老师，欢迎您
				<a href="chooseCourse.jsp" style="text-decoration:none">课程首页</a>
				<a href="exit.jsp" style="text-decoration:none">退出</a>
			</div>
			
			<div align="center">
				<div align="center" style="width:1050px">
					<p align="left"><a href="QueryQuestionServlet"><%=course_name %></a></p></div>
				<table class="aa">
					<tr>
						<th id="th1" align="center" onclick="di1()" onmouseover="di1()" <%=(choice==1)?"style='background:#77b;color:#fff'":"style='background:#bbb;color:#000'" %> >
							<span id="interaction">互动区</span></th>
						<th id="th2" align="center" onclick="di2()" onmouseover="di2()" <%=(choice==2)?"style='background:#77b;color:#fff'":"style='background:#bbb;color:#000'" %>>
							<span id="library">题库区</span></th>
						<th id="th3" align="center" onclick="di3()" onmouseover="di3()" <%=(choice==3)?"style='background:#77b;color:#fff'":"style='background:#bbb;color:#000'" %>>
							<span id="statistics">统计区</span></th>
					</tr>
					<tr>
						<td colspan="3">
							<div id="div1" align="center" <%=(choice==1)?"style='min-height:800px;display:block'":"style='min-height:800px;display:none'" %> >
							<!--	互动区展示内容	-->
								
								<p align="right">
									<a href="/yixueServer/Interaction/addQuestion.jsp" target="_blank" title="点击添加问题">添加问题</a>
								</p>
								
								<%
									List<Question_Bean> questions = (List<Question_Bean>)request.getAttribute("questions");
									if(questions!=null){ //查找到了数据  %>
										<table ><!--class="table table-striped table-bordered table-hover"  -->
											<thead>
												<tr>
													<td style="width: 200px;height: 40px">主题</td>
													<td width="250px">内容</td>
													<td width="200px">时间</td>
													<td width="200px">状态</td>
													<td width="200px">操作</td>
												</tr>
											</thead>
										<%	
											int q_pageNum = 1;
											try {
												q_pageNum = Integer.parseInt((String)request.getAttribute("q_pageNum"));
											} catch (NumberFormatException e) {
												   e.printStackTrace(); 
											}
											int q_totalPage = 1;
											try {
												q_totalPage = Integer.parseInt((String)request.getAttribute("q_totalPage"));
											} catch (NumberFormatException e) {
												e.printStackTrace();
											}
											Question_Bean question = null;
											int q_startPos = (q_pageNum-1)*10; %>
											<tbody>
										<%	
											for(int i=q_startPos;i<questions.size()&&i<q_startPos+10;i++){ 
												question = questions.get(i); %>
												<tr>
													<td style="width: 200px;height: 40px"><%=question.getTheme() %></td>
													<td width="250px"><%=question.getContent() %></td>
													<td width="200px"><%=question.getTime() %></td>
													<%	
														if(question.getStatus()==0 ){ %>
															<td width="200px">未发布</td>
															<td width="200px">
																<a href="Interaction/checkQuestion.jsp?theme=<%=question.getTheme() %>&content=<%=question.getContent() %>" target="_blank" style="text-decoration: none">查看</a>|
																<a href="AlterQuestionStatusServlet?q_id=<%=question.getQ_id() %>&status=1&q_pageNum=<%=q_pageNum %>" style="text-decoration: none">发布</a>|
																<a href="/yixueServer/Interaction/alterQuestion.jsp?q_id=<%=question.getQ_id()%>&theme=<%=question.getTheme()%>&content=<%=question.getContent()%>" style="text-decoration: none">修改</a>|
																<a href="javascript:deleteQuestion('<%=question.getTheme() %>',<%=question.getQ_id()%>)" style="text-decoration: none">删除</a>
															</td>
													<%	}else if(question.getStatus()==2){ %>
															<td width="200px"><span style="color:#ff0000">已发布</span></td>
															<td width="200px">
																<a href="Interaction/checkQuestion.jsp?theme=<%=question.getTheme() %>&content=<%=question.getContent() %>" target="_blank" style="text-decoration: none">查看</a>|
																<a href="javascript:deleteQuestion('<%=question.getTheme() %>',<%=question.getQ_id()%>)" style="text-decoration: none">删除</a>
															</td>
													<%	}else{ %>
															<td width="200px"><span style="color:#0000ff">发布中</span></td>
															<td width="200px">
																<a href="Interaction/showQuestion.jsp?q_id=<%=question.getQ_id() %>&theme=<%=question.getTheme() %>&content=<%=question.getContent() %>" style="text-decoration: none">查看</a>|
																<a href="AlterQuestionStatusServlet?q_id=<%=question.getQ_id() %>&status=2&q_pageNum=<%=q_pageNum %>" style="text-decoration: none">结束</a>
															</td>
													<%	}
													%>
												</tr>
										<%	} %>
											<tr>
												<td colspan="5">
													<br>
													<div align="center">
														<a href="QueryQuestionServlet?q_pageNum=1" style="text-decoration: none">首页</a>
														<%
															if(q_pageNum==1){ %>
																上一页
														<%	}else{ %>
																<a href="QueryQuestionServlet?pageNum=<%=q_pageNum-1%>" style="text-decoration: none">上一页</a>
														<%	}
															if(q_pageNum==q_totalPage){ %>
																下一页
														<%	}else{ %>
																<a href="QueryQuestionServlet?q_pageNum=<%=q_pageNum+1%>" style="text-decoration: none">下一页</a>
														<%	}
														%>
																
														<select id="questionPage" onchange="questionPageChange()">
														<%
															for(int j=1;j<=q_totalPage;j++){ %>
																<option value="<%=j %>" <%=(q_pageNum==j)?"selected":"" %>> 第<%=j %>页</option>
														<%	}
														%>
														</select>
														共<%=q_totalPage %>页
															<a href="QueryQuestionServlet?q_pageNum=<%=q_totalPage%>" style="text-decoration: none">末页</a>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									<%	}else{ %>
											<p align="center">暂无相关信息</p>
									<%	}
									%>			
							</div>
		
		
							
							<div id="div2" align="center" <%=(choice==2)?"style='min-height:800px;display:block'":"style='min-height:800px;display:none'" %> >
								<!--	题库区展示内容 	-->
		
								<p align="right">
									<a href="/yixueServer/Library/addSelection.jsp" target="_blank" title="点击添加问题">添加问题</a>
								</p>
									<%
										List<Selection_Bean> selections = (List<Selection_Bean>) request.getAttribute("selections");
										if(selections!=null){ %>
											<table border="0">
											<tr>
												<td width="200px">主题</td>
												<td width="250px">内容</td>
												<td width="200px">选项</td>
												<td width="100px">分值</td>
												<td width="100px">状态</td>
												<td width="200px">操作</td>
											</tr>
										<%	int s_pageNum = 1;
											try {
												s_pageNum = Integer.parseInt((String) request.getAttribute("s_pageNum"));
											} catch (NumberFormatException e) {
												e.printStackTrace();
												s_pageNum = 1;
											}
											int s_totalPage = 1;
											try {
												s_totalPage = Integer.parseInt((String) request.getAttribute("s_totalPage"));
											} catch (NumberFormatException e) {
												e.printStackTrace();
												s_totalPage = 1;
											}
											Selection_Bean selection = null;
											int startPos = (s_pageNum-1)*10;
											for(int i=startPos;i<selections.size()&&i<startPos+10;i++){ 
												selection = selections.get(i);
											 %>
												<tr>
													<td width="200px"><%=selection.getTheme() %></td>
													<td width="250px"><%=selection.getContent() %></td>										
													<td width="200px">
														A.<%=selection.getA() %><br/>
														B.<%=selection.getB() %><br/>
														C.<%=selection.getC() %><br/>
														D.<%=selection.getD() %>
													</td>
													<td width="100px"><%=selection.getScore() %></td>
													<%
														if(selection.getStatus()==0){ %>
															<td width="100px">未发布</td>
															<td width="200px">
																<a href="QuerySelectionServlet?s_id=<%=selection.getS_id()%>&s_pageNum=<%=s_pageNum%>&status=0" target="_blank" style="text-decoration:none">查看</a>|
																<a href="javascript:publishSelection('<%=selection.getTheme() %>',<%=selection.getS_id() %>,<%=s_pageNum %>)" style="text-decoration:none">发布</a>|
																<a href="Library/alterSelection.jsp?s_id=<%=selection.getS_id() %>&theme=<%=selection.getTheme() %>&content=<%=selection.getContent() %>&A=<%=selection.getA() %>&B=<%=selection.getB() %>&C=<%=selection.getC() %>&D=<%=selection.getD() %>&correctChoice=<%=selection.getCorrectChoice() %>&score=<%=selection.getScore() %>&minutes=<%=selection.getMinutes() %>" style="text-decoration:none">修改</a>|
																<a href="javascript:deleteSelection('<%=selection.getTheme() %>',<%=selection.getS_id() %>)" style="text-decoration:none">删除</a>
															</td>
													<%	}else if(selection.getStatus()==1){ %>
															<td width="100px"><span style="color:#0000ff">发布中</span></td>
															<td width="200px">
																<a href="QuerySelectionServlet?s_id=<%=selection.getS_id()%>&s_pageNum=<%=s_pageNum%>&status=1" style="text-decoration:none">查看</a>|
																<a href="FinishSelectionServlet?s_id=<%=selection.getS_id() %>&s_pageNum=<%=s_pageNum %>" style="text-decoration:none">结束</a>
															</td>
													<%	}else{ %>
															<td width="100px"><span style="color: #ff0000">已发布</span></td>
															<td width="200px">
																<a href="showStatisticsPicture.jsp?s_id=<%=selection.getS_id() %>" target="_blank" style="text-decoration:none">查看</a>|
																<a href="javascript:deleteSelection('<%=selection.getTheme() %>',<%=selection.getS_id() %>)" style="text-decoration:none">删除</a>
															</td>
													<%	}
													%>
												</tr>
											<%} %>
											</table>
											<br>
											<div align="center">
												<a href="QueryAllSelectionServlet?s_pageNum=1&choice=2" style="text-decoration: none">
													首页</a>
												<%
													if(s_pageNum==1){ %>
														上一页
												<%	}else{ %>
														<a href="QueryAllSelectionServlet?s_pageNum=<%=s_pageNum-1%>&choice=2" style="text-decoration: none">
															上一页</a>
												<%	}
													if(s_pageNum==s_totalPage){ %>
														下一页
												<%	}else{ %>
														<a href="QueryAllSelectionServlet?s_pageNum=<%=s_pageNum+1%>&choice=2" style="text-decoration: none">
															下一页</a>
												<%	}
												%>
												
													<select id="selectionPage" onchange="selectionPageChange()">
														<%
															for(int j=1;j<=s_totalPage;j++){ %>
																<option value="<%=j %>" <%=(s_pageNum==j)?"selected":"" %>> 第<%=j %>页</option>
														<%	}
														%>
													</select>
												共<%=s_totalPage %>页
												<a href="QueryAllSelectionServlet?s_pageNum=<%=s_totalPage%>&choice=2" style="text-decoration: none">
													末页</a>
											</div>
									<%	}else{ %>
											<p align="center">暂无相关信息</p>
									<%	}
									%>
							</div>
							
							
							<div id="div3" <%=(choice==3)?"style='min-height:800px;display:block'":"style='min-height:800px;display:none'" %> >
							<!--	统计区展示内容 	-->
								<br>
								<table>
										<%
											int i = 0;
										%>
										<c:forEach var="student" items="${students}">
											<%
												if(i%7==0){ %>
													<tr>
											<%	}
											%>
												<td align="center" style="width:150px;background: #ffd700">
													<a href="QueryStudentInfoServlet?student_number=${student.student_number}&student_name=${student.student_name}" target="_blank">
														${student.student_number}<br>${student.student_name}											
													</a></td>
											<%
												if((i+1)%7==0){ %>
													<tr/>
											<%	}
												i++;
											%>
										</c:forEach>
										<%
											if(i%7!=0){ %>
												<tr/>
										<%	}
										%>
									</table>	
							</div>
							
							
						</td>
					</tr>
				</table>
				<jsp:include page="foot.jsp"></jsp:include>
			</div>
	<%	}
	%>
	

</body>
<script type="text/javascript">
	function di1() {
		with(document){
			getElementById("div1").style.display = "block";
			getElementById("div2").style.display = "none";
			getElementById("div3").style.display = "none";
			
			getElementById("th1").style.background = "#77b";
			getElementById("th2").style.background = "#bbb";
			getElementById("th3").style.background = "#bbb";
			
			getElementById("interaction").style.color = "#fff";
			getElementById("library").style.color = "#000";
			getElementById("statistics").style.color = "#000";
		}
	}
	function di2() {
		with(document){
			getElementById("div1").style.display = "none";
			getElementById("div2").style.display = "block";
			getElementById("div3").style.display = "none";
			
			getElementById("th1").style.background = "#bbb";
			getElementById("th2").style.background = "#77b";
			getElementById("th3").style.background = "#bbb";
			
			getElementById("interaction").style.color = "#000";
			getElementById("library").style.color = "#fff";
			getElementById("statistics").style.color = "#000";
		}
	}
	function di3() {
		with(document){
			getElementById("div1").style.display = "none";
			getElementById("div2").style.display = "none";
			getElementById("div3").style.display = "block";
			
			getElementById("th1").style.background = "#bbb";
			getElementById("th2").style.background = "#bbb";
			getElementById("th3").style.background = "#77b";
			
			getElementById("interaction").style.color = "#000";
			getElementById("library").style.color = "#000";
			getElementById("statistics").style.color = "#fff";
		}
	}
</script>

<script type="text/javascript">
	function deleteQuestion(theme,q_id){
		var del = confirm('确定删除“'+theme+'”吗？');
		if(del){
			var url = "DeleteQuestionServlet?q_id=" + q_id;
			window.location.href = url;
		}
	}
	function deleteSelection(theme,s_id){
		var del = confirm('确定删除“'+theme+'”吗？');
		if(del){
			var url = "DeleteSelectionServlet?s_id=" + s_id;
			window.location.href = url;
		}
	}
	function publishSelection(theme,s_id,s_pageNum){
		var del = confirm('确定发布“'+theme+'”吗？');
		if(del){
			var url = "JudgeSelectionServlet?s_id=" + s_id + "&s_pageNum=" + s_pageNum;
			window.location.href = url;
		}
	}
</script>

<script type="text/javascript">
	function questionPageChange(){
		var pa = document.getElementById("questionPage").value;
		var url = "QueryQuestionServlet?course_number=<%=course_number%>&class_number=<%=class_number%>&q_pageNum=" + pa;
		window.location.href = url;
	}
	function selectionPageChange(){
		var pa = document.getElementById("selectionPage").value;
		var url = "QueryAllSelectionServlet?course_number=<%=course_number%>&class_number=<%=class_number%>&s_pageNum=" + pa + "&choice=2";
		window.location.href = url;
	}
</script>
</html>