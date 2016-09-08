package com.yixueserver.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.StudentDao;
import com.yixueserver.dao.TeacherDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}

	/**
	 * 老师、学生登录
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		String tag = request.getParameter("tag");
		//老师学生登录
			String teacher_number = request.getParameter("teacher_number");
			String student_number = request.getParameter("student_number");
			String password = request.getParameter("password");
			String student_name = null;
			String teacher_name = null;
			if(student_number!=null)
				student_name = StudentDao.login(student_number, password);
			else if(teacher_number!=null)
				teacher_name = TeacherDao.login(teacher_number, password);
			if (teacher_name != null){
				HttpSession session = request.getSession();
				session.setAttribute("teacher_number", teacher_number);
				session.setAttribute("teacher_name", teacher_name);
				session.setMaxInactiveInterval(45*60); //session最大有效期为45分钟
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else if (student_name != null){
				HttpSession session = request.getSession();
				session.setAttribute("student_number", student_number);
				session.setAttribute("student_name", student_name);
				session.setMaxInactiveInterval(45*60); //session最大有效期为45分钟
				request.getRequestDispatcher("/Slogin.jsp").forward(request, response);
			}
			else
				request.setAttribute("tag", "failed");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}
	}
