package com.yixueserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yixueserver.dao.QuestionDao;

@WebServlet(name = "ajaxgetcontent", urlPatterns = { "/ajaxgetcontent" })
public class ajaxGetContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ajaxGetContent() {// 得到内容和题目
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置编码

		ResultSet resultSet = null;
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();

		resultSet = QuestionDao.selectContentByID(Integer.parseInt(request
				.getParameter("q_id")));
		try {
			System.out.println(resultSet);
			pw.format("<p >%s</p><p >%s</p>", resultSet.getString("title"),
					resultSet.getString("content"));
			pw.flush();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}