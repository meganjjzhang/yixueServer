package com.yixueserver.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

	public static Connection getConnection(){
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

	public static void main(String[] args) {
		
		System.out.println(getConnection());
	}
}