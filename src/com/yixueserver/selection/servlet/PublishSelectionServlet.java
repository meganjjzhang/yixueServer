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
	 *��ѡ�����ⷢ��ʱ�����servlet
	 **/
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
				if(s_id!=0){ //s_id��Ч��ֻ�Ǽ򵥵��жϣ�
					Selection_Bean selection_Bean = SelectionDao.querySelectionById(s_id, course_number, class_number,0);
					if(selection_Bean!=null){ //��ѯ����Ϣ
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
						//��������ɹ�����ת��չʾҳ��
						if(SelectionDao.publishSelection(s_id, course_number, class_number, theme, content, choice, correctChoice, score, minutes1)){
							Selection_Bean selection = SelectionDao.querySelectionById(s_id, course_number, class_number,1);
							if(selection!=null){ //��ѯ���˷���������
								long remainTime = selection.getStartTime() + selection.getMinutes() * 60 * 1000 - System.currentTimeMillis();
								if(remainTime<=0){ //�ý���������
									request.getRequestDispatcher("/FinishSelectionServlet?s_id="+s_id).forward(request, response);
								}else{ //���ܼ�������
									long minutes = remainTime / 1000 / 60;
									long seconds = remainTime / 1000 % 60;
									request.setAttribute("minutes", minutes+"");
									request.setAttribute("seconds", seconds+"");
									request.setAttribute("selection", selection);
									request.getRequestDispatcher("/Library/publishSelection.jsp").forward(request, response);
								}
							}else{ //δ��ѯ������������
								request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
							}
							request.getRequestDispatcher("/Library/publishSelection.jsp").forward(request, response);
						}
						else{ //����ʧ�ܣ����»�ȥ
							request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
						}
					}
					else //δ��ѯ������ת��ȥ
						request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
				}
				else //s_id������
					request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
			}
			else //�γ���Ϣ��Ч�����ؿγ���ҳ
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
		}
		else //δ��¼��session���ڣ���ת���µ�¼
			request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
