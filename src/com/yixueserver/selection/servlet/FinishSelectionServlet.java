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
	 * ��������ķ����������޸�����δ�����ѧϰ��¼
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){ //��¼��Ч
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if(course_number!=null && class_number!=null){ //�γ���Ϣ��Ч
				int s_id = 0;
				try {
					s_id = Integer.parseInt(request.getParameter("s_id"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				SelectionDao.finishSelection(s_id, course_number, class_number);
				request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
			}else{ //�γ���Ϣ��Ч�����ؿγ���ҳ
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}
		}else{ //δ��¼��session�ѹ���
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
