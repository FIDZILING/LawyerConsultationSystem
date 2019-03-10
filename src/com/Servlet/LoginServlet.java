package com.Servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.CsDAO;
import com.DAO.UserDAO;
import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		if ("userlogin".equals(method)) {
			userlogin(request, response);
		}
		if ("cslogin".equals(method)) {
			cslogin(request, response);
		}
		if ("logout".equals(method)) {
			logout(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		request.getSession().removeAttribute("name");
		request.getSession().removeAttribute("type");
	}

	private void userlogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Writer out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		String res = userDAO.UserLogin(username, password);
		String resJson = "";
		if (res == "success") {
			request.getSession().setAttribute("name", username);
			request.getSession().setAttribute("type", "user");
			resJson = "success";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		} else if (res == "密码错误") {
			resJson = "密码错误";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		} else if (res == "用户名不存在") {
			resJson = "用户名不存在";
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

	private void cslogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Writer out = response.getWriter();
		String csname = request.getParameter("csname");
		String password = request.getParameter("password");
		CsDAO csDAO = new CsDAO();
		String res = csDAO.CsLogin(csname, password);
		String resJson = "";
		if (res == "success") {
			request.getSession().setAttribute("name", csname);
			request.getSession().setAttribute("type", "cs");
			resJson = "success";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		} else if (res == "密码错误") {
			resJson = "密码错误";
			resJson = JSON.toJSONString(resJson);
			out.write(resJson);
			out.flush();
			out.close();
		} else if (res == "用户名不存在") {
			resJson = "用户名不存在";
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
