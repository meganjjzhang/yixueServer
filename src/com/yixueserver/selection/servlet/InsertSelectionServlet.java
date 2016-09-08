package com.yixueserver.selection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.SelectionDao;

@WebServlet("/InsertSelectionServlet")
public class InsertSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/**
	 * 插入有选项问题
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if(course_number!=null && class_number!=null){
				String theme = request.getParameter("theme");
				String content = request.getParameter("content");
				String A = request.getParameter("A");
				String B = request.getParameter("B");
				String C = request.getParameter("C");
				String D = request.getParameter("D");
				String correctChoice = request.getParameter("correctChoice");
				int score = 1;
				try {
					score = Integer.parseInt(request.getParameter("score"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					score = 1;
				}
				int minutes = 45;
				try {
					minutes = Integer.parseInt(request.getParameter("minutes"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					minutes = 45;
				}
				if(SelectionDao.insertSelectionInfo(course_number, class_number, theme, content, A, B, C, D, correctChoice, 0, 0, 0, 0, score, minutes))
					request.setAttribute("tag", "succeeded");
				else //添加失败
					request.setAttribute("tag", "failed");
				request.getRequestDispatcher("/Library/addSelection_ok.jsp").forward(request, response);
			}else{ //课堂信息有误，重新选择课堂
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}
		}else{ //session过期，跳转重新登录
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
