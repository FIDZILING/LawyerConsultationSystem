package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Link.*;

public class UserDAO {
	public String UserLogin(String username, String password) {
		String res = "";
		Connection conn = Link.getConn();
		Statement state = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user where username = '" + username + "'";// SQL语句,
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					res = "success";
				} else {
					res = "密码错误";
				}
			} else {
				res = "用户名不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Link.close(rs, state, conn);
		}
		return res;
	}

	public String UserSignup(String username, String password) {
		Connection conn = Link.getConn();
		Statement state = null;
		ResultSet rs = null;
		String result = null;
		try {
			// 先查看有没有该用户名
			String sql = "select username from user where username = '" + username + "'";// SQL语句,
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (!rs.next()) {
				// 没有则插入
				sql = "insert into user(username,password) values(?,?)";// SQL语句,
				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setString(1, username);
				stmt.setString(2, password);
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = "success";
					return result;
				} else {
					result = "服务器错误";
					return result;
				}
			} else {
				result = "存在用户名";
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Link.close(state, conn);
		}
		result = "服务器错误";
		return result;
	}

	public boolean CheckSignup(String username) {
		Connection conn = Link.getConn();
		Statement state = null;
		ResultSet rs = null;
		try {
			String sql = "select username from user where username = '" + username + "'";// SQL语句,
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Link.close(state, conn);
		}
		return false;
	}
}
