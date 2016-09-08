package com.yixueserver.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.StudentDao;
import com.yixueserver.po.Student_Bean;

@WebServlet("/QueryStudentServlet")
public class QueryStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number==null)
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		else{
			String course_number = request.getParameter("course_number");
			String class_number = request.getParameter("class_number");
			List<Student_Bean> students = StudentDao.queryStudent(course_number, class_number);
			request.setAttribute("students", students);
			request.getRequestDispatcher("/functionHome.jsp?choice=4").forward(request, response);
		}
	}
}
