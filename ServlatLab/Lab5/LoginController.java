package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginController extends HttpServlet{
	public static Map<String, String> users;
	static {
		users = new HashMap<String, String>();
		users.put("admin", "admin");
		users.put("kael", "1234");
		users.put("system", "manager");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		PrintWriter pw = resp.getWriter();
		String username = (String) req.getParameter("username");
		String password = (String) req.getParameter("password");
		if(users.containsKey(username) && users.get(username).equals(password)) {
		//if( username.equals("admin") && password.equals("admin") ) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			resp.sendRedirect("./report.html");
		}else {
			pw.println("Please check your username and password combination");
		}
		pw.close();
	}
}