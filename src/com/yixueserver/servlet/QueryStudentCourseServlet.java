package com.yixueserver.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.yixueserver.dao.StudentDao;
import com.yixueserver.po.Course_Bean;

@WebServlet("/QueryStudentCourseServlet")
public class QueryStudentCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 查询学生课程信息，为手机客户端应用服务
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String student_number = request.getParameter("student_number");
		if(student_number!=null){
			List<Course_Bean> list = StudentDao.queryCourse(student_number);
			if(list==null)
				response.getOutputStream().write("failed".getBytes("utf-8"));
			else{
				JSONArray json = JSONArray.fromObject(list);
				String jStr = json.toString();
				response.getOutputStream().write(jStr.getBytes("utf-8"));
			}
		}
	}
}
