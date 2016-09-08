package com.yixueserver.selection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixueserver.dao.SelectionDao;
import com.yixueserver.po.Selection_Bean;

@WebServlet("/QuerySelectionServlet")
public class QuerySelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 查询未发布或发布中的问题
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
				int status = -1;
				try {
					status = Integer.parseInt(request.getParameter("status"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if(s_id!=0 && status!=-1){ //s_id、status有效（简单判断）
					if(status==1){ //查询发布中问题
						Selection_Bean selection = SelectionDao.querySelectionById(s_id, course_number, class_number,1);
						if(selection!=null){ //查询到了发布中问题
							long remainTime = selection.getStartTime() + selection.getMinutes() * 60 * 1000 - System.currentTimeMillis();
							if(remainTime<=0){ //该结束答题了
								request.getRequestDispatcher("/FinishSelectionServlet?s_id="+s_id).forward(request, response);
							}else{ //还能继续答题
								long minutes = remainTime / 1000 / 60;
								long seconds = remainTime / 1000 % 60;
								request.setAttribute("minutes", minutes+"");
								request.setAttribute("seconds", seconds+"");
								request.setAttribute("selection", selection);
								request.getRequestDispatcher("/Library/publishSelection.jsp").forward(request, response);
							}
						}else{ //未查询到发布中问题
							request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
						}
					}else if(status==0){ //查询未发布问题
						Selection_Bean selection = SelectionDao.querySelectionById(s_id, course_number, class_number, 0);
						if(selection!=null){
							request.setAttribute("selection", selection);
							request.getRequestDispatcher("/Library/checkSelection.jsp").forward(request, response);
						}else{ //未查询到未发布问题
							request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
						}
					}else{ //状态信息有误
						request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
					}
				}else{ //s_id或status无效
					request.getRequestDispatcher("/QueryAllSelectionServlet").forward(request, response);
				}
			}else{ //课程信息无效，重新进入课程
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}
		}else{ //未登录或session已过期
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
