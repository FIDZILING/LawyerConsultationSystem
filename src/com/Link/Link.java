package com.Link;

import java.sql.*;

public class Link {
	public static String db_url = "jdbc:mysql://localhost:3306/layerconsultation?useUnicode=true&characterEncoding=UTF-8";
	public static String db_user = "root";
	public static String db_password = "123456";

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载驱动
			conn = DriverManager.getConnection(db_url, db_user, db_password);// 连接数据库
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Statement state, Connection conn) {
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs, Statement state, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
