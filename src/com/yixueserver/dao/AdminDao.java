package com.yixueserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yixueserver.po.Course_Bean;
import com.yixueserver.po.Student_Bean;
import com.yixueserver.util.DBManager;

public class AdminDao {	/*管理员登录验证*/

	public static String login(String admin_number, String password) {

		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String admin_name = null;
		try {
			pstmt = con.prepareStatement("select admin_name from administrator where admin_number=? and password=?");
			pstmt.setString(1, admin_number);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				admin_name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return admin_name;
	}
	
	/*查询学生课程*/
	public static List<Course_Bean> queryCourse(String student_number) {

		List<Course_Bean> list = null;
		Course_Bean info = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select distinct course_number,class_number,course_name,classroom,course_time from courses where student_number=?");
			pstmt.setString(1, student_number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<Course_Bean>();
				info = new Course_Bean();
				info.setCourse_number(rs.getString("course_number"));
				info.setClass_number(rs.getString("class_number"));
				info.setCourse_name(rs.getString("course_name"));
				info.setClassroom(rs.getString("classroom"));
				info.setCourse_time(rs.getString("course_time"));
				list.add(info);
			}
			while (rs.next()) {
				info = new Course_Bean();
				info.setCourse_number(rs.getString("course_number"));
				info.setClass_number(rs.getString("class_number"));
				info.setCourse_name(rs.getString("course_name"));
				info.setClassroom(rs.getString("classroom"));
				info.setCourse_time(rs.getString("course_time"));
				list.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/* 查询学生信息 */
	public static List<Student_Bean> queryStudent(String course_number,
			String class_number) {

		List<Student_Bean> list = null;
		Student_Bean bean = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con
					.prepareStatement("select student_number,student_name from courses where course_number=? and class_number=?");
			pstmt.setString(1, course_number);
			pstmt.setString(2, class_number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<Student_Bean>();
				bean = new Student_Bean();
				bean.setStudent_number(rs.getString("student_number"));
				bean.setStudent_name(rs.getString("student_name"));
				list.add(bean);
			}
			while (rs.next()) {
				bean = new Student_Bean();
				bean.setStudent_number(rs.getString("student_number"));
				bean.setStudent_name(rs.getString("student_name"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(queryStudent("8701","1"));
	}
}
