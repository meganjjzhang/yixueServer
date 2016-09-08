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
	 * ��ѯ��ʦ��ѧ���Ŀγ���Ϣ
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		String student_number = (String) session.getAttribute("student_number");
		if(teacher_number!=null){ //��ʦ��¼��Ч
			List<Course_Bean> infos = TeacherDao.queryCourse(teacher_number);
			request.setAttribute("infos", infos);
			request.getRequestDispatcher("/courseInfo.jsp").forward(request, response);
		}
		else if(student_number!=null){ //ѧ����¼��Ч
			List<Course_Bean> infos = TeacherDao.queryCourse(student_number);
			request.setAttribute("infos", infos);
			request.getRequestDispatcher("/ScourseInfo.jsp").forward(request, response);
		}
		else{ //δ��¼��session�ѹ���
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
