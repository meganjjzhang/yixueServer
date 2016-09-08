<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.yixueserver.po.*"%>
<%@ page import="com.yixueserver.dao.*"%>
<%@ page import="com.yixueserver.util.*"%>
<%@ page import="java.util.*,java.util.List,java.text.NumberFormat"%>
<%@ page
	import="org.jfree.chart.ChartFactory,
org.jfree.chart.JFreeChart,
org.jfree.chart.plot.PlotOrientation,
org.jfree.chart.servlet.ServletUtilities,
org.jfree.data.category.CategoryDataset,
org.jfree.chart.plot.*,
org.jfree.chart.labels.*,
java.awt.*,org.jfree.ui.*,
org.jfree.data.category.DefaultCategoryDataset,
org.jfree.data.general.PieDataset,
org.jfree.chart.renderer.category.BarRenderer3D,java.awt.*,
org.jfree.chart.axis.CategoryAxis,
org.jfree.data.general.DefaultPieDataset"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	String teacher_number = (String)session.getAttribute("teacher_number");
	String course_number = (String)session.getAttribute("course_number");
	String class_number = (String)session.getAttribute("class_number");
	int s_id = 0;
	try{
		s_id = Integer.parseInt(request.getParameter("s_id"));
	} catch(NumberFormatException e){
		e.printStackTrace();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示答题统计信息</title>
</head>
<body>
	<%
	if(teacher_number!=null){ %>
		<div align="right">
			<select id="choose" onchange="javascript:chooseChanged()">
				<option value="全部">全部</option>
				<option value="柱状图">柱状图</option>
				<option value="扇形图">扇形图</option>
			</select>
		</div>

	<%
		if(course_number==null || class_number==null){ //课堂信息出错，跳转重新选择课堂
			response.setHeader("refresh", "1;QueryCourseServlet");
		}else{ //课堂信息有效的情况
// 			生成柱状图图形
			
			int a=0,b=0,c=0,d=0;
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			Selection_Bean selection = SelectionDao.querySelectionById(s_id,course_number,class_number,2);
			if(selection==null){ //无相应信息
				
			}else {
				a = selection.getAA();
				b = selection.getBB();
				c = selection.getCC();
				d = selection.getDD();

				dataset.addValue(a, "A", "");
				dataset.addValue(b, "B", "");
				dataset.addValue(c, "C", "");
				dataset.addValue(d, "D", "");
			}//else

			//设置地区、销量的显示位置  			
			String filename = ServletUtilities.saveChartAsPNG(ChartUtil.createChart(dataset), 500, 300, null, session);
			String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;			
		%>
<!-- 			显示图片 -->
			<div align="center" id="chartshow1">
				<img src="<%=graphURL%>" width=600 height=600 border=0 usemap="#<%= filename %>">
				<br><br><br><br>
			</div>
			
<!-- 			生成扇形图图形 -->
			<%
				DefaultPieDataset dataset2 = new DefaultPieDataset();
				if(selection==null){ //无相应信息
					
				}else{
					a = selection.getAA();
					b = selection.getBB();
					c = selection.getCC();
					d = selection.getDD();
					
					dataset2.setValue("A", a);  
			        dataset2.setValue("B", b);  
			        dataset2.setValue("C", c);
			        dataset2.setValue("D", d);
				}
				  //设置地区、销量的显示位置  			
		        String filename2 = ServletUtilities.saveChartAsPNG(ChartUtil2.createChart2(dataset2), 500, 300,  null, session);  
				String graphURL2 = request.getContextPath()  + "/DisplayChart?filename=" + filename2;  
			%>
			<div align="center" id="chartshow2">
				<img src="<%=graphURL2%>" width=600 height=400 border=0 usemap="#<%= filename2 %>">
			</div>
	<%	}
	}else{ //未登录或session已过期 %>
		<p align="center">登录超时，请重新登录后再进行相应操作</p>
<%		response.setHeader("refresh", "1;URL=login.jsp");
	}
%>

</body>
<script type="text/javascript">
	function chooseChanged(){
		var choo = document.getElementById("choose").value;
		var chart1 = document.getElementById("chartshow1");
		var chart2 = document.getElementById("chartshow2");
		if(choo=="全部"){
			chart1.style.display = "block";
			chart2.style.display = "block";
		}else if(choo=="柱状图"){
			chart1.style.display = "block";
			chart2.style.display = "none";
		}else{
			chart1.style.display = "none";
			chart2.style.display = "block";
		}
	}
</script>
</html>