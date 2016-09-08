package com.yixueserver.selection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.SelectionDao;

@WebServlet("/FinishSelectionServlet")
public class FinishSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 结束问题的发布，并且修改所有未答题的学习记录
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){ //登录有效
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if(course_number!=null && class_number!=null){ //课程信息有效
				int s_id = 0;
				try {
					s_id = Integer.parseInt(request.getParameter("s_id"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				SelectionDao.finishSelection(s_id, course_number, class_number);
				request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
			}else{ //课程信息无效，返回课程首页
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}
		}else{ //未登录或session已过期
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
