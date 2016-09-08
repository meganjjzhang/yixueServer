package com.yixueserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yixueserver.po.Course_Bean;
import com.yixueserver.util.DBManager;

public class TeacherDao {
	
	public static String login(String teacher_number,String password){
		
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String teacher_name = null;
		try {
			pstmt = con.prepareStatement("select teacher_name from teachers where teacher_number=? and password=?");
			pstmt.setString(1, teacher_number);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				teacher_name=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(rs!=null){
					rs.close();
					rs = null;
				}
				if(pstmt!=null){
					pstmt.close();
					pstmt = null;
				}
				if(con!=null){
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return teacher_name;
	}
	
	/*查询教师课程*/
	public static List<Course_Bean> queryCourse(String teacher_number){
		
		List<Course_Bean> list = null;
		Course_Bean info = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select distinct course_number,class_number,course_name,classroom,course_time from courses where teacher_number=?");
			pstmt.setString(1, teacher_number);
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<Course_Bean>();
				info = new Course_Bean();
				info.setCourse_number(rs.getString("course_number"));
				info.setClass_number(rs.getString("class_number"));
				info.setCourse_name(rs.getString("course_name"));
				info.setClassroom(rs.getString("classroom"));
				info.setCourse_time(rs.getString("course_time"));
				list.add(info);
			}
			while(rs.next()){
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
		}finally{
			try {
				if(rs!=null){
					rs.close();
					rs = null;
				}
				if(pstmt!=null){
					pstmt.close();
					pstmt = null;
				}
				if(con!=null){
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
		
		System.out.println(login("2012","2012"));
	//	System.out.println(queryCourse("2000").size());
	}
}
