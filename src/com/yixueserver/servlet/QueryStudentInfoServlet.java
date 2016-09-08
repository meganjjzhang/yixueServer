package com.yixueserver.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.RecordDao;
import com.yixueserver.dao.ScoreDao;
import com.yixueserver.po.Record_Bean;

@WebServlet("/QueryStudentInfoServlet")
public class QueryStudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 查询有关学生的相关信息
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){ //登录有效
			String student_number = request.getParameter("student_number");
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if(course_number!=null && class_number!=null){ //课程信息有效
				double totalScore = ScoreDao.queryScore(student_number, course_number, class_number);
				List<Record_Bean> records = RecordDao.queryRecord(student_number, course_number, class_number);
				int pageNum = 1;
				try {
					pageNum = Integer.parseInt(request.getParameter("pageNum"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				int totalPage = 1;
				try {
					totalPage = records.size()/10+(records.size()%10==0?0:1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(pageNum>totalPage)
					pageNum = totalPage;
				request.setAttribute("pageNum", pageNum+"");
				request.setAttribute("totalPage", totalPage+"");
				request.setAttribute("totalScore", totalScore+"");
				request.setAttribute("records", records);
				request.getRequestDispatcher("/Statistics/studentInfo.jsp").forward(request, response);
			}else{ //课程信息有误
				request.getRequestDispatcher("/chooseCourse.jsp").forward(request, response);
			}
		}else{ //未登录或session已过期
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
