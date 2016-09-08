package com.yixueserver.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �������ݿ���࣬��ʱδ�ø��࣬�����Dao���з������룬����ʹ�ø���
 * 2015-03-11
 **/
public class DB {

	/**
	 * @param void
	 * @return Connection con
	 * ��ȡ��MySQL���ݿ������
	 **/
	public static Connection getConnection() {

		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/yixuedb?useUnicode=true&characterEncoding=gb2312";
		String driver = "com.mysql.jdbc.Driver";
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return con;
	}

	/**
	 * @param Connection con
	 * @param String sql
	 * @return Statement ps
	 * ��ȡStatement
	 **/
	public static PreparedStatement getPreparedStatement(Connection con, String sql) {

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return ps;
		}
		return ps;
	}

	/**
	 * @param PreparedStatement ps
	 * @return ResultSet rs
	 * ��ȡ��ѯ���ݵĽ����
	 **/
	public static ResultSet getResultSet(PreparedStatement ps) {

		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}

	/**
	 * @param PreparedStatement ps
	 * @return boolean
	 * ִ�в��롢���¡�ɾ������
	 **/
	public static boolean execute(PreparedStatement ps) {
		
		int tag = 0;
		if(ps!=null){
			try {
				tag = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if(tag==0)
			return false;
		else
			return true;
	}
	
	/**
	 * @param ResultSet rs
	 * @return void
	 * �رս����
	 **/
	public static void close(ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param PreparedStatement ps
	 * @return void
	 * �ر�PreparedStatement
	 **/
	public static void close(PreparedStatement ps) {

		if (ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param Connection con
	 * @return void
	 * �ر�����
	 **/
	public static void close(Connection con) {

		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
