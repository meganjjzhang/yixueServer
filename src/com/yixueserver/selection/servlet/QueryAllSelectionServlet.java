package com.yixueserver.selection.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.QuestionDao;
import com.yixueserver.dao.SelectionDao;
import com.yixueserver.dao.StudentDao;
import com.yixueserver.po.Question_Bean;
import com.yixueserver.po.Selection_Bean;
import com.yixueserver.po.Student_Bean;

@WebServlet("/QueryAllSelectionServlet")

public class QueryAllSelectionServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 查询所有有选项问题，同时查询所有无选项问题和课堂学生
	 * */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number==null){ //重新登录
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if(course_number==null && class_number==null){ //重新进入课堂
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}else{
				List<Selection_Bean> selections = SelectionDao.querySelection(course_number, class_number);
				int s_pageNum = 1;
				try {
					s_pageNum = Integer.parseInt(request.getParameter("s_pageNum"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				int s_totalPage = 1;
				try {
					s_totalPage = selections.size()/10+(selections.size()%10==0?0:1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(s_pageNum>s_totalPage)
					s_pageNum = s_totalPage;
				/*题库区数据*/
				request.setAttribute("selections", selections);
				request.setAttribute("s_pageNum", s_pageNum+"");
				request.setAttribute("s_totalPage", s_totalPage+"");
				
				/*互动区数据*/
				List<Question_Bean> questions = QuestionDao.queryQuestion(course_number, class_number);// 未发布问题
				int q_pageNum = 1;
				try {
					q_pageNum = Integer.parseInt(request.getParameter("q_pageNum"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				int q_totalPage = 1;
				try {
					q_totalPage = questions.size()/10+(questions.size()%10==0?0:1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(q_pageNum>q_totalPage)
					q_pageNum = q_totalPage;
				request.setAttribute("questions", questions);
				request.setAttribute("q_pageNum", q_pageNum+"");
				request.setAttribute("q_totalPage", q_totalPage+"");
				
				/*统计区数据*/
				List<Student_Bean> students = StudentDao.queryStudent(course_number, class_number);
				request.setAttribute("students", students);
				request.getRequestDispatcher("/functionHome.jsp?choice=2").forward(request, response);
			}
		}
	}
}
