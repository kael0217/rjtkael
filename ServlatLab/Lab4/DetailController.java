package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailController
 */
@WebServlet("/ShowDetail")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("firstname: "+request.getParameter("firstname")+"<br/>");
		pw.println("lastname: "+request.getParameter("lastname")+"<br/>");
		pw.println("Gender: "+request.getParameter("gender")+"<br/>");
		if(request.getParameterValues("skills")!=null) {
			pw.println("Skills: "+Arrays.toString(request.getParameterValues("skills"))+"<br/>");
		}else {
			pw.println("Skills: none "+"<br/>");
		}
		pw.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
