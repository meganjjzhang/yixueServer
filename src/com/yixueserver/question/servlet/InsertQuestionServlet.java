package com.yixueserver.question.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.QuestionDao;

@WebServlet("/InsertQuestionServlet")
public class InsertQuestionServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if(course_number != null && class_number != null ){
				String theme=request.getParameter("theme");
				String content=request.getParameter("content");
				if(QuestionDao.insertQuestion(course_number, class_number, theme, content)){
					request.setAttribute("tag", "succeeded");
				}else{
					request.setAttribute("tag", "failed");
				}
				request.getRequestDispatcher("/Interaction/addQuestion_ok.jsp").forward(request, response);
			}
			else 
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
		} else{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}