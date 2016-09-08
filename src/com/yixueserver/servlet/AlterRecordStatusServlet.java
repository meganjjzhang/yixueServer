package com.yixueserver.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yixueserver.dao.RecordDao;

@WebServlet("/AlterRecordStatusServlet")
public class AlterRecordStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 学生回答问题，修改相应的信息，为手机客户端应用服务
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int r_id = 0;
		int s_id = 0;
		int status = 0;
		long endTime = 0;
		try {
			r_id = Integer.parseInt(request.getParameter("r_id"));
			s_id = Integer.parseInt(request.getParameter("s_id"));
			status = Integer.parseInt(request.getParameter("status"));
			endTime = Long.parseLong(request.getParameter("endTime"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if(r_id!=0 && s_id!=0){
			if(endTime!=0){ //信息有效
				String student_number = request.getParameter("student_number");
				String course_number = request.getParameter("course_number");
				String class_number = request.getParameter("class_number");
				if(endTime>=System.currentTimeMillis()){ //答题有效
					String myChoice = request.getParameter("myChoice");
					double myScore = 0;
					try {
						myScore = Double.parseDouble(request.getParameter("myScore"));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					if(student_number!=null && course_number!=null && class_number!=null){
						if(RecordDao.alterRecordStatus(student_number, course_number, class_number, r_id,s_id, myChoice, myScore, status))
							response.getOutputStream().write("succeed".getBytes("utf-8"));
						else
							response.getOutputStream().write("failed".getBytes("utf-8"));
					}
					else{
						response.getOutputStream().write("failed".getBytes("utf-8"));
					}
				}else{ //答题时间已过
					if(RecordDao.alterRecordStatus(r_id, student_number, course_number, class_number))
						response.getOutputStream().write("succeed".getBytes("utf-8"));
					else
						response.getOutputStream().write("failed".getBytes("utf-8"));
				}
			}else{ //信息无效
				
			}
		}else{
			response.getOutputStream().write("failed".getBytes("utf-8"));
		}
	}
}
