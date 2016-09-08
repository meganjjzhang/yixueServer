package com.yixueserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yixueserver.util.DB;
import com.yixueserver.util.DBManager;

public class ScoreDao {

	/**
	 * @param String student_number
	 * @param String course_number
	 * @param String class_number
	 * @return List<Record_Bean>
	 * 查询课堂中某个学生的总分
	 * */
	public static double queryScore(String student_number,String course_number,String class_number) {

		Connection con = DBManager.getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		double score = -1;
		try {
			pstmt = con
					.prepareStatement("select totalScore from scores where student_number=? and course_number=? and class_number=?");
			pstmt.setString(1, student_number);
			pstmt.setString(2, course_number);
			pstmt.setString(3, class_number);
			rs = pstmt.executeQuery();
			if (rs.next())
				score = rs.getDouble(1);
			if(score==-1){
				pst = con.prepareStatement("insert into scores values(?,?,?,0)");
				pst.setString(1, student_number);
				pst.setString(2, course_number);
				pst.setString(3, class_number);
				pst.executeUpdate();
				score = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DB.close(rs);
			DB.close(pstmt);
			DB.close(pst);
			DB.close(con);
		}
		if(score==-1)
			return 0;
		else
			return score;
	}
}
