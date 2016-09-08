package com.yixueserver.question.servlet;

import java.io.IOException;
//import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpSession;

import com.yixueserver.dao.QuestionDao;

@WebServlet("/AlterQuestionInfoServlet")
public class AlterQuestionInfoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * �޸���ѡ������
	 * */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_number = (String) session.getAttribute("teacher_number");
		if(teacher_number!=null){ //��¼��Ч
			int q_id = 0;
			try{
				q_id=Integer.parseInt(request.getParameter("q_id"));
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			if(q_id!=0){ //q_id��Ч�����жϣ�
				String theme = request.getParameter("theme");
				String content = request.getParameter("content");
				if(theme!=null && content!=null){ //���ĵ���Ϣ��Ч
					if(QuestionDao.alterQuestionInfo(q_id, theme, content)){ //�޸ĳɹ�
						request.setAttribute("tag", "succeeded");
					}else{ //�޸�ʧ��
						request.setAttribute("tag", "failed");
					}
				}else{ //���ĵ���Ϣ��Ч
					request.setAttribute("tag", "failed");
				}
				request.getRequestDispatcher("/Interaction/alterQuestion_ok.jsp").forward(request, response);
			}else{ //q_id��Ч
				request.getRequestDispatcher("/QueryQuestionServlet").forward(request, response);
			}
		}else{ //δ��¼��session�ѹ���
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}