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

@WebServlet("/PublishSelectionServlet")
public class PublishSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 *有选项问题发布时处理的servlet
	 **/
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
				if(s_id!=0){ //s_id有效（只是简单的判断）
					Selection_Bean selection_Bean = SelectionDao.querySelectionById(s_id, course_number, class_number,0);
					if(selection_Bean!=null){ //查询到信息
						String theme = selection_Bean.getTheme();
						String content = selection_Bean.getContent();
						String choice = "A." + selection_Bean.getA() + "\\\\n"
								+ "B." + selection_Bean.getB() + "\\\\n"
								+ "C." + selection_Bean.getC() + "\\\\n"
								+ "D." + selection_Bean.getD();
						System.out.println(choice);
						String correctChoice = selection_Bean.getCorrectChoice();
						int score = selection_Bean.getScore();
						int minutes1 = selection_Bean.getMinutes();
						//发布问题成功，跳转到展示页面
						if(SelectionDao.publishSelection(s_id, course_number, class_number, theme, content, choice, correctChoice, score, minutes1)){
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
							request.getRequestDispatcher("/Library/publishSelection.jsp").forward(request, response);
						}
						else{ //发布失败，重新回去
							request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
						}
					}
					else //未查询到，跳转回去
						request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
				}
				else //s_id号有误
					request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
			}
			else //课程信息无效，返回课程首页
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
		}
		else //未登录或session过期，跳转重新登录
			request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
