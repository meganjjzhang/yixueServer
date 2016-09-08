package com.yixueserver.selection.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.SelectionDao;
import com.yixueserver.po.Selection_Bean;

@WebServlet("/JudgeSelectionServlet")
public class JudgeSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 发布有选项问题时，用来判断是否已经存在发布中问题，保证始终只有一个问题处于发布中
	 * */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if (teacher_number != null) { // 登录有效
			String course_number = (String) session.getAttribute("course_number");
			String class_number = (String) session.getAttribute("class_number");
			if (course_number != null && class_number != null) { // 课程信息有效
				int s_id = 0;
				try {
					s_id = Integer.parseInt(request.getParameter("s_id"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if (s_id != 0) { // s_id有效（只是简单的判断）
					List<Selection_Bean> selections = SelectionDao.querySelection(course_number, class_number, 1);
					if (selections != null) { // 查询到了发布中问题
						request.getRequestDispatcher("/Library/judgeSelection_ok.jsp").forward(request, response);
					} else { // 未查询到发布中问题
						request.getRequestDispatcher("/PublishSelectionServlet").forward(request, response);
					}
				} else { // s_id号有误
					request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
				}
			} else { // 课程信息无效，返回课程首页
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}
		} else { // 未登录或session已过期
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}
	}
}
