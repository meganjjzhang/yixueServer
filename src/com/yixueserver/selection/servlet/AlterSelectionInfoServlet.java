package com.yixueserver.selection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.SelectionDao;

@WebServlet("/AlterSelectionInfoServlet")
public class AlterSelectionInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){
			int s_id = 0;
			try {
				s_id = Integer.parseInt(request.getParameter("s_id"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				s_id = 0;
			}
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
			if(SelectionDao.alterSelectionInfo(s_id, theme, content, A, B, C, D, correctChoice, score, minutes))
				request.setAttribute("tag", "succeeded");
			else
				request.setAttribute("tag", "failed");
			request.getRequestDispatcher("/Library/alterSelection_ok.jsp").forward(request, response);
		}
		else
			request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
