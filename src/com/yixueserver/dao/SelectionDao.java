package com.yixueserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yixueserver.po.Selection_Bean;
import com.yixueserver.util.DB;
import com.yixueserver.util.DBManager;

public class SelectionDao {

	/**
	 * @param string course_number
	 * @param string class_number
	 * @param int status
	 * @return List<Selection_Bean> list 
	 * 通过课程号、课堂号、状态来查询有选项问题
	 * */
	public static List<Selection_Bean> querySelection(String course_number,
			String class_number, int status) {

		List<Selection_Bean> list = null;
		Selection_Bean bean = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con
					.prepareStatement("select * from selections where course_number=? and class_number=? and status=?");
			pstmt.setString(1, course_number);
			pstmt.setString(2, class_number);
			pstmt.setInt(3, status);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<Selection_Bean>();
				bean = new Selection_Bean();
				bean.setS_id(rs.getInt("s_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setA(rs.getString("A"));
				bean.setB(rs.getString("B"));
				bean.setC(rs.getString("C"));
				bean.setD(rs.getString("D"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setAA(rs.getInt("AA"));
				bean.setBB(rs.getInt("BB"));
				bean.setCC(rs.getInt("CC"));
				bean.setDD(rs.getInt("DD"));
				bean.setScore(rs.getInt("score"));
				bean.setMinutes(rs.getInt("minutes"));
				bean.setStatus(status);
				bean.setTime(rs.getString("time"));
				list.add(bean);
			}
			while (rs.next()) {
				bean = new Selection_Bean();
				bean.setS_id(rs.getInt("s_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setA(rs.getString("A"));
				bean.setB(rs.getString("B"));
				bean.setC(rs.getString("C"));
				bean.setD(rs.getString("D"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setAA(rs.getInt("AA"));
				bean.setBB(rs.getInt("BB"));
				bean.setCC(rs.getInt("CC"));
				bean.setDD(rs.getInt("DD"));
				bean.setScore(rs.getInt("score"));
				bean.setMinutes(rs.getInt("minutes"));
				bean.setStatus(status);
				bean.setTime(rs.getString("time"));
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

	/**
	 * @param string course_number
	 * @param string class_number
	 * @return List<Selection_Bean> list 
	 * 通过课程号、课堂号来查询有选项问题
	 * */
	public static List<Selection_Bean> querySelection(String course_number,
			String class_number) {

		List<Selection_Bean> list = null;
		Selection_Bean bean = null;
		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con
					.prepareStatement("select * from selections where course_number=? and class_number=?");
			pstmt.setString(1, course_number);
			pstmt.setString(2, class_number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<Selection_Bean>();
				bean = new Selection_Bean();
				bean.setS_id(rs.getInt("s_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setA(rs.getString("A"));
				bean.setB(rs.getString("B"));
				bean.setC(rs.getString("C"));
				bean.setD(rs.getString("D"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setAA(rs.getInt("AA"));
				bean.setBB(rs.getInt("BB"));
				bean.setCC(rs.getInt("CC"));
				bean.setDD(rs.getInt("DD"));
				bean.setScore(rs.getInt("score"));
				bean.setMinutes(rs.getInt("minutes"));
				bean.setStatus(rs.getInt("status"));
				bean.setTime(rs.getString("time"));
				list.add(bean);
			}
			while (rs.next()) {
				bean = new Selection_Bean();
				bean.setS_id(rs.getInt("s_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setA(rs.getString("A"));
				bean.setB(rs.getString("B"));
				bean.setC(rs.getString("C"));
				bean.setD(rs.getString("D"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setAA(rs.getInt("AA"));
				bean.setBB(rs.getInt("BB"));
				bean.setCC(rs.getInt("CC"));
				bean.setDD(rs.getInt("DD"));
				bean.setScore(rs.getInt("score"));
				bean.setMinutes(rs.getInt("minutes"));
				bean.setStatus(rs.getInt("status"));
				bean.setTime(rs.getString("time"));
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

	/**
	 * @param int s_id
	 * @param String course_number
	 * @param String class_number
	 * @param int status
	 * @return Selection_Bean bean 
	 * 通过s_id查询课堂中问题
	 **/
	public static Selection_Bean querySelectionById(int s_id,String course_number,String class_number,int status) {

		Selection_Bean bean = null;
		Connection con = DB.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from selections where s_id="+ s_id + " and course_number=" + course_number +" and class_number=" + class_number + " and status=" + status;
		ps = DB.getPreparedStatement(con, sql);
		rs = DB.getResultSet(ps);
		try {
			if (rs.next()) {
				bean = new Selection_Bean();
				bean.setS_id(rs.getInt("s_id"));
				bean.setTheme(rs.getString("theme"));
				bean.setContent(rs.getString("content"));
				bean.setA(rs.getString("A"));
				bean.setB(rs.getString("B"));
				bean.setC(rs.getString("C"));
				bean.setD(rs.getString("D"));
				bean.setCorrectChoice(rs.getString("correctChoice"));
				bean.setAA(rs.getInt("AA"));
				bean.setBB(rs.getInt("BB"));
				bean.setCC(rs.getInt("CC"));
				bean.setDD(rs.getInt("DD"));
				bean.setScore(rs.getInt("score"));
				bean.setMinutes(rs.getInt("minutes"));
				bean.setStartTime(rs.getTimestamp("startTime").getTime());
				bean.setStatus(rs.getInt("status"));
				bean.setTime(rs.getString("time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return bean;
		} finally {
			
		}
		return bean;
	}

	public static boolean alterSelectionInfo(int s_id, String theme,
			String content, String A, String B, String C, String D,
			String correctChoice, int score, int minutes) {

		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		int tag = 0;
		try {
			pstmt = con
					.prepareStatement("update selections set theme=?,content=?,A=?,B=?,C=?,D=?,correctChoice=?,score=?,minutes=?,time=now() where s_id=?");
			pstmt.setString(1, theme);
			pstmt.setString(2, content);
			pstmt.setString(3, A);
			pstmt.setString(4, B);
			pstmt.setString(5, C);
			pstmt.setString(6, D);
			pstmt.setString(7, correctChoice);
			pstmt.setInt(8, score);
			pstmt.setInt(9, minutes);
			pstmt.setInt(10, s_id);
			tag = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
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
		if (tag == 0)
			return false;
		else
			return true;
	}

	/**
	 * @param int s_id
	 * @param String
	 *            course_number
	 * @param String
	 *            class_number
	 * @param String
	 *            theme
	 * @param String
	 *            content
	 * @param String
	 *            choice
	 * @param String
	 *            correctChoice
	 * @param int score
	 * @param int minutes
	 * @return boolean 发布问题，同时为该课堂的学生添加相应的记录
	 **/
	public static boolean publishSelection(int s_id, String course_number,
			String class_number, String theme, String content, String choice,
			String correctChoice, int score, int minutes) {

		Connection con = DB.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 60 * minutes * 1000; // 计算结束时间
		try {
			con.setAutoCommit(false); // 自动提交设置为false
			String sql = "select student_number from courses where course_number=? and class_number=?";
			ps = DB.getPreparedStatement(con, sql);
			ps.setString(1, course_number);
			ps.setString(2, class_number);
			rs = ps.executeQuery(); // 查询到该课堂的所有学生学号
			String sql1 = null;
			while (rs.next()) { // 为每一个学生建立一条记录
				sql1 = ("insert into records values (null," + s_id + ",'"
						+ rs.getString("student_number") + "','"
						+ course_number + "','" + class_number + "','" + theme
						+ "','" + content + "','" + choice + "','"
						+ correctChoice + "','无'," + score + "," + "0,?" + ",0)");
				ps1 = DB.getPreparedStatement(con, sql1);
				ps1.setTimestamp(1, new Timestamp(endTime));
				ps1.executeUpdate();
			}
			String sql2 = "update selections set status=1,startTime=? where s_id=?";
			ps2 = DB.getPreparedStatement(con, sql2);
			ps2.setTimestamp(1, new Timestamp(startTime));
			ps2.setInt(2, s_id);
			ps2.executeUpdate();
			con.commit(); // 提交所有的操作
			con.setAutoCommit(true); // 将自动提交还原为true
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DB.close(rs);
			DB.close(ps);
			DB.close(ps1);
			DB.close(ps2);
			DB.close(con);
		}
		return true;
	}

	/**
	 * @param int s_id
	 * @param String
	 *            course_number
	 * @param String
	 *            class_number
	 * @return boolean 结束答题，将问题状态置为2，同时将该课堂所有未答题的学生记录由0置为2,1为其自己在规定时间内答了题
	 **/
	public static boolean finishSelection(int s_id, String course_number,
			String class_number) {

		Connection con = DB.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		boolean tag = false;
		try {
			con.setAutoCommit(false);
			String sql = "update records set myChoice='无',myScore=0,status=2 where s_id=? and course_number=? and class_number=? and status=0";
			ps = DB.getPreparedStatement(con, sql);
			ps.setInt(1, s_id);
			ps.setString(2, course_number);
			ps.setString(3, class_number);
			DB.execute(ps);
			String sql1 = "update selections set status=2 where s_id=" + s_id +" and course_number=" + course_number + " and class_number=" + class_number;
			ps1 = DB.getPreparedStatement(con, sql1);
			tag = DB.execute(ps1);
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DB.close(ps);
			DB.close(ps1);
			DB.close(con);
		}
		return tag;
	}

	public static boolean alterSelectionStatus(int s_id, int status) {

		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		int tag = 0;
		try {
			pstmt = con
					.prepareStatement("update selections set status=?,starttime=now() where s_id=?");
			pstmt.setInt(1, status);
			pstmt.setInt(2, s_id);
			tag = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
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
		if (tag == 0)
			return false;
		else
			return true;
	}

	public static boolean insertSelectionInfo(String course_number,
			String class_number, String theme, String content, String A,
			String B, String C, String D, String correctChoice, int AA, int BB,
			int CC, int DD, int score, int minutes) {

		Connection con = (Connection) DBManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con
					.prepareStatement("insert into selections values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,0,now())");
			pstmt.setString(1, course_number);
			pstmt.setString(2, class_number);
			pstmt.setString(3, theme);
			pstmt.setString(4, content);
			pstmt.setString(5, A);
			pstmt.setString(6, B);
			pstmt.setString(7, C);
			pstmt.setString(8, D);
			pstmt.setString(9, correctChoice);
			pstmt.setInt(10, AA);
			pstmt.setInt(11, BB);
			pstmt.setInt(12, CC);
			pstmt.setInt(13, DD);
			pstmt.setInt(14, score);
			pstmt.setInt(15, minutes);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
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
		return true;
	}

	/**
	 * @param int s_id
	 * @param String course_number
	 * @param String class_number
	 * @return boolean 
	 * 通过s_id删除课堂有选项问题
	 * */
	public static boolean deleteSelection(int s_id,String course_number,String class_number) {

		Connection con = DB.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from selections where s_id=" + s_id + " and course_number=" + course_number + " and class_number=" + class_number;
		System.out.println(sql);
		ps = DB.getPreparedStatement(con, sql);
		boolean tag = DB.execute(ps);
		DB.close(ps);
		DB.close(con);
		return tag;
	}
}
