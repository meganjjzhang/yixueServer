package com.yixueserver.question.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.QuestionDao;

@WebServlet("/AlterQuestionStatusServlet")
public class AlterQuestionStatusServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

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
		if(teacher_number!=null) {
			int q_id = 0;
			try{
				q_id=Integer.parseInt(request.getParameter("q_id"));
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			if(q_id!=0){
				int status = 0;
				try {
					status = Integer.parseInt(request.getParameter("status")) ;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if(status!=0 ){
					QuestionDao.alterQuestionStatus(q_id, status);
				}
			}
			request.getRequestDispatcher("/QueryQuestionServlet").forward(request, response);
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}

