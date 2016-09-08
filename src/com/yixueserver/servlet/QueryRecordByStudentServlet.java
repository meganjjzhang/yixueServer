package com.yixueserver.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.yixueserver.dao.RecordDao;
import com.yixueserver.po.Record_Bean2;

@WebServlet("/QueryRecordByStudentServlet")
public class QueryRecordByStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 查询学生答题记录信息，为手机客户端应用服务
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String student_number = request.getParameter("student_number");
		String course_number = request.getParameter("course_number");
		String class_number = request.getParameter("class_number");
		if(student_number!=null && course_number!=null && class_number!=null){ //信息有效
			Record_Bean2 bean = RecordDao.querySelectionByStudent(student_number, course_number, class_number);
			if(bean!=null){
				JSONArray json = JSONArray.fromObject(bean);
				String jStr = json.toString();
				response.getOutputStream().write(jStr.getBytes("utf-8"));
			}
			else
				response.getOutputStream().write("failed".getBytes("utf-8"));
		}else{ //信息有误
			response.getOutputStream().write("failed".getBytes("utf-8"));
		}
	}
}