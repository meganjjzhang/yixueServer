package com.yixueserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yixueserver.po.Question_Bean;
import com.yixueserver.util.DB;
import com.yixueserver.util.DBManager;

public class QuestionDao {

	//通过课程编号和课堂号查询无选项问题
	public static List<Question_Bean> queryQuestion(String course_number,String class_number){
		List<Question_Bean> list = null;
		Question_Bean bean = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select q_id,theme,content,time,status from questions where course_number=? and class_number=?");
			pstmt.setString(1, course_number);
			pstmt.setString(2,class_number);
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<Question_Bean>();
				bean = new Question_Bean();
				bean.setQ_id(rs.getInt("q_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setTime(rs.getString("time"));
				bean.setStatus(rs.getInt("status"));
				list.add(bean);
			}
			while(rs.next()){
				bean = new Question_Bean();
				bean.setQ_id(rs.getInt("q_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setTime(rs.getString("time"));
				bean.setStatus(rs.getInt("status"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
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
	
	//插入一条问题记录
	public static boolean insertQuestion(String course_number,String class_number,String theme,String content){
		
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("insert into questions values(null,?,?,?,?,0,now())");
			pstmt.setString(1, course_number);
			pstmt.setString(2, class_number);
			pstmt.setString(3, theme);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
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
		return true;
	}
	
	//修改无选项问题信息
	public static boolean alterQuestionInfo(int q_id,String theme,String content){
		
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		int tag = 0;
		try {
			pstmt = con.prepareStatement("update questions set theme=?,content=?,time=now() where q_id=?");
			pstmt.setString(1,theme);
			pstmt.setString(2, content);
			pstmt.setInt(3, q_id);
			tag = pstmt.executeUpdate();
			System.out.println(tag);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
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
		if(tag == 0)
			return false;
		else
			return true;
	}
	
	//修改无选项问题状态
	public static boolean alterQuestionStatus(int q_id,int status){
		
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		int tag = 0;
		try {
			pstmt = con.prepareStatement("update questions set status=? where q_id=?");
			pstmt.setInt(1, status);
			pstmt.setInt(2, q_id);
			tag = pstmt.executeUpdate();
			System.out.println(tag);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
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
		if(tag == 0)
			return false;
		else
			return true;
	}
	
	// 得到问题的题目和内容
	public static ResultSet selectContentByID(int id) {// 根据号找内容
		ResultSet resultSet = null;
		try {
			Connection conn = DBManager.getConnection();
			Statement statement;
			statement = conn.createStatement();
			resultSet = statement.executeQuery(String.format(
					"SELECT * from questions WHERE q_id=%d;", id));
			if (!resultSet.first())
				return null;
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * @param int q_id
	 * @param String course_number
	 * @param String class_number
	 * @return boolean 
	 * 通过q_id删除课堂无选项问题
	 * */
	public static boolean deleteQuestion(int q_id,String course_number,String class_number){
		
		Connection con = DB.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from questions where q_id=" + q_id + " and course_number=" + course_number + " and class_number=" + class_number;
		System.out.println(sql);
		ps = DB.getPreparedStatement(con, sql);
		boolean tag = DB.execute(ps);
		DB.close(ps);
		DB.close(con);
		return tag;
	}
	
	public static void main(String[] args){
	
	//	System.out.println(insertQuestion("9901","2","作业一","大家赶快做"));
	//	System.out.println(alterQuestionInfo(1,"作业一","大家可以下次交"));
	//	System.out.println(alterQuestionStatus(1,1));
	//	System.out.println(queryQuestion(null,null,2).size());
//		System.out.println(deleteQuestion(3,"8701","1"));
	} 
}
