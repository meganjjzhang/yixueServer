package com.yixueserver.selection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.SelectionDao;

@WebServlet("/AlterSelectionStatusServlet")
public class AlterSelectionStatusServlet extends HttpServlet {
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
			}
			if(s_id!=0){
				int status = 0;
				try {
					status = Integer.parseInt(request.getParameter("status"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if(status==0)
					request.getRequestDispatcher("/QuerySelectionServlet").forward(request, response);
				else if(status==1)
				SelectionDao.alterSelectionStatus(s_id, status);
			}
			else
				request.getRequestDispatcher("/QuerySelectionServlet").forward(request, response);
		}
		else //session已过期，跳转重新登录
			request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
