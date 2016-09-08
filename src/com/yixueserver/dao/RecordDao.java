package com.yixueserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yixueserver.po.Record_Bean;
import com.yixueserver.po.Record_Bean2;
import com.yixueserver.util.DB;
import com.yixueserver.util.DBManager;

public class RecordDao {

	/**
	 * @param String student_number
	 * @param String course_number
	 * @param String class_number
	 * @return List<Record_Bean> list
	 * 查询课堂中某个学生的答题情况
	 * */
	public static List<Record_Bean> queryRecord(String student_number,String course_number,String class_number){
		
		List<Record_Bean> list = null;
		Record_Bean bean = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select theme,content,choice,correctChoice,myChoice,score,myScore,status from records where student_number=? and course_number=? and class_number=?");
			pstmt.setString(1, student_number);
			pstmt.setString(2, course_number);
			pstmt.setString(3, class_number);
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<Record_Bean>();
				bean = new Record_Bean();
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setChoice(rs.getString("choice"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setMyChoice(rs.getString("myChoice"));
				bean.setScore(rs.getInt("score"));
				bean.setMyScore(rs.getInt("myScore"));
				bean.setStatus(rs.getInt("status"));
				list.add(bean);
			}
			while(rs.next()){
				bean = new Record_Bean();
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setChoice(rs.getString("choice"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setMyChoice(rs.getString("myChoice"));
				bean.setScore(rs.getInt("score"));
				bean.setMyScore(rs.getInt("myScore"));
				bean.setStatus(rs.getInt("status"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally{
			DB.close(rs);
			DB.close(pstmt);
			DB.close(con);
		}
		return list;
	}
	
	/**
	 * @param String student_number
	 * @param String course_number
	 * @param String class_number
	 * @return Record_Bean
	 * 查询学生答题记录
	 * */
	public static Record_Bean2 querySelectionByStudent(String student_number,String course_number,String class_number){
		
		Record_Bean2 bean = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from records where student_number=? and course_number=? and class_number=? and status=0");
			pstmt.setString(1, student_number);
			pstmt.setString(2, course_number);
			pstmt.setString(3, class_number);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean = new Record_Bean2();
				bean.setR_id(rs.getInt("r_id"));
				bean.setS_id(rs.getInt("s_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setChoice(rs.getString("choice"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setScore(rs.getInt("score"));
				bean.setEndTime(rs.getTimestamp("endTime").getTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return bean;
		} finally{
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
		return bean;
	}
	
	/**
	 * @param String student_number
	 * @param String course_number
	 * @param String class_number
	 * @param int r_id
	 * @param int s_id
	 * @param String myChoice
	 * @param double myScore
	 * @param int status
	 * @return boolean
	 * 修改我的选择、得分和记录状态，顺便修改学生课程得分和相应问题选项数
	 * */
	public static boolean alterRecordStatus(String student_number,String course_number,String class_number,int r_id,int s_id,String myChoice,double myScore,int status){
		
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		PreparedStatement pst4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		double totalScore = 0;
		try {
			con.setAutoCommit(false);
			//更新学生记录信息
			pstmt = con.prepareStatement("update records set myChoice=?,myScore=?,status=? where r_id=?");
			pstmt.setString(1, myChoice);
			pstmt.setDouble(2, myScore);
			pstmt.setInt(3, status);
			pstmt.setInt(4, r_id);
			pstmt.executeUpdate();
			//查询学生总分
			pst1 = con.prepareStatement("select totalScore from scores where student_number=? and course_number=? and class_number=?");
			pst1.setString(1, student_number);
			pst1.setString(2, course_number);
			pst1.setString(3, class_number);
			rs = pst1.executeQuery();
			if(rs.next())
				totalScore = rs.getInt(1);
			//更新学生总分
			pst2 = con.prepareStatement("update scores set totalScore=? where student_number=? and course_number=? and class_number=?");
			pst2.setDouble(1, totalScore+myScore);
			pst2.setString(2, student_number);
			pst2.setString(3, course_number);
			pst2.setString(4, class_number);
			pst2.executeUpdate();
			//查询并且更新问题选项数
			String sql1 = "select ";
			String sql2 = "update selections set ";
			String AA = null;
			String BB = null;
			String CC = null;
			String DD = null;
			if(myChoice.contains("A")){
				sql1 += ",AA";
				sql2 += ",AA=?";
				AA = "AA";
			}
			if(myChoice.contains("B")){
				sql1 += ",BB";
				sql2 += ",BB=?";
				BB = "BB";
			}
			if(myChoice.contains("C")){
				sql1 += ",CC";
				sql2 += ",CC=?";
				CC = "CC";
			}
			if(myChoice.contains("D")){
				sql1 += ",DD";
				sql2 += ",DD=?";
				DD = "DD";
			}
			sql1 = sql1.replace(" ,", " ");
			sql2 = sql2.replace(" ,", " ");
			sql1 += " from selections where s_id=? and course_number=? and class_number=?";
			sql2 += " where s_id=? and course_number=? and class_number=?";
			pst3 = con.prepareStatement(sql1);
			pst3.setInt(1, s_id);
			pst3.setString(2, course_number);
			pst3.setString(3, class_number);
			rs2 = pst3.executeQuery();
			pst4 = con.prepareStatement(sql2);
			int index = 1;
			if(rs2.next()){
				if(AA!=null){
					pst4.setInt(index, rs2.getInt("AA")+1);
					System.out.println(AA+index);
					index++;
				}
				if(BB!=null){
					pst4.setInt(index, rs2.getInt("BB")+1);
					System.out.println(BB+index);
					index++;
				}
				if(CC!=null){
					pst4.setInt(index, rs2.getInt("CC")+1);
					System.out.println(CC+index);
					index++;
				}
				if(DD!=null){
					pst4.setInt(index, rs2.getInt("DD")+1);
					System.out.println(DD+index);
					index++;
				}
			}
			pst4.setInt(index, s_id);
			index++;
			pst4.setString(index, course_number);
			index++;
			pst4.setString(index, class_number);
			pst4.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			DB.close(rs);
			DB.close(rs2);
			DB.close(pst1);
			DB.close(pst2);
			DB.close(pst3);
			DB.close(pst4);
			DB.close(con);
		}
		return true;
	}
	
	/**
	 * @param int r_id
	 * @param String student_number
	 * @param String course_number
	 * @param String class_number
	 * @return boolean
	 * 学生未在规定时间内答题时修改记录状态
	 * */
	public static boolean alterRecordStatus(int r_id,String student_number,String course_number,String class_number){
		
		Connection con  = DB.getConnection();
		String sql = "update records set status=2 where r_id=" + r_id 
				+ " and student_number=" + student_number 
				+ " and course_number=" + course_number 
				+ " and class_number=" + class_number;
		PreparedStatement ps = DB.getPreparedStatement(con, sql);
		boolean tag = DB.execute(ps);
		DB.close(ps);
		DB.close(con);
		return tag;
	}
}
