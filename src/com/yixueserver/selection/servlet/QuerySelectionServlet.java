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
	 * ��ѯδ�����򷢲��е�����
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
				int status = -1;
				try {
					status = Integer.parseInt(request.getParameter("status"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if(s_id!=0 && status!=-1){ //s_id��status��Ч�����жϣ�
					if(status==1){ //��ѯ����������
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
					}else if(status==0){ //��ѯδ��������
						Selection_Bean selection = SelectionDao.querySelectionById(s_id, course_number, class_number, 0);
						if(selection!=null){
							request.setAttribute("selection", selection);
							request.getRequestDispatcher("/Library/checkSelection.jsp").forward(request, response);
						}else{ //δ��ѯ��δ��������
							request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
						}
					}else{ //״̬��Ϣ����
						request.getRequestDispatcher("/QueryAllSelectionServlet?choice=2").forward(request, response);
					}
				}else{ //s_id��status��Ч
					request.getRequestDispatcher("/QueryAllSelectionServlet").forward(request, response);
				}
			}else{ //�γ���Ϣ��Ч�����½���γ�
				request.getRequestDispatcher("/QueryCourseServlet").forward(request, response);
			}
		}else{ //δ��¼��session�ѹ���
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
