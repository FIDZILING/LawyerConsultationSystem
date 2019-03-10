package com.Servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.UserDAO;
import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");// 设置字符集，避免乱码
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");
		if ("usersignup".equals(method)) {
			usersignup(request, response);
		}
		if ("checksignup".equals(method)) {
			checksignup(request, response);
		}
	}

	private void checksignup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Writer out = response.getWriter();
		String username = request.getParameter("username");
		UserDAO userDAO = new UserDAO();
		boolean res = userDAO.CheckSignup(username);
		String resJson = "";
		if (res) {
			resJson = "yes";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		} else {
			resJson = "no";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		}
	}

	private void usersignup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Writer out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		String res = userDAO.UserSignup(username, password);
		String resJson = "";
		if (res == "success") {
			request.getSession().setAttribute("username", username);
			resJson = "success";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		} else if (res == "存在用户名") {
			resJson = "存在用户名";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		} else {
			resJson = "服务器错误";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		}
	}

}
