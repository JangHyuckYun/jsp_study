package com.servlet_test2.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {
	public static Connection conn = null;
	public static PreparedStatement stmt = null;
	public static ResultSet rs = null;

	public static Connection connection() {
		String className = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sublet_project";
		String user = "root";
		String password = "0000";

		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void close(Connection conn, PreparedStatement stmt) {
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
