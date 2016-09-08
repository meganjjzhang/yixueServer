package com.yixueserver.question.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.QuestionDao;

@WebServlet("/DeleteQuestionServlet")
public class DeleteQuestionServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 删除无选项问题
	 * */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){ //登录有效
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if(course_number!=null && class_number!=null){ //课堂信息有效
				int q_id = 0;
				try{
					q_id=Integer.parseInt(request.getParameter("q_id"));
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
				if(q_id!=0){
					QuestionDao.deleteQuestion(q_id, course_number, class_number);
				}
				request.getRequestDispatcher("/QueryQuestionServlet").forward(request, response);
			}else{ //课堂信息无效
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}
		}else{ //未登录或session已过期
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
