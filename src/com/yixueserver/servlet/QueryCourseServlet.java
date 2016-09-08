package com.yixueserver.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.TeacherDao;
import com.yixueserver.po.Course_Bean;

@WebServlet("/QueryCourseServlet")
public class QueryCourseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {
		doPost(request,response);
	}

	/**
	 * 查询老师及学生的课程信息
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		String student_number = (String) session.getAttribute("student_number");
		if(teacher_number!=null){ //教师登录有效
			List<Course_Bean> infos = TeacherDao.queryCourse(teacher_number);
			request.setAttribute("infos", infos);
			request.getRequestDispatcher("/courseInfo.jsp").forward(request, response);
		}
		else if(student_number!=null){ //学生登录有效
			List<Course_Bean> infos = TeacherDao.queryCourse(student_number);
			request.setAttribute("infos", infos);
			request.getRequestDispatcher("/ScourseInfo.jsp").forward(request, response);
		}
		else{ //未登录或session已过期
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
