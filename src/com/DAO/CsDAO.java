package com.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Link.Link;

public class CsDAO {
	public String CsLogin(String csname, String password) {
		String res = "";
		Connection conn = Link.getConn();
		Statement state = null;
		ResultSet rs = null;
		try {
			String sql = "select * from customerserv where csname = '" + csname + "'";// SQL语句,
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
}
