package com.java.controller;

import java.util.List;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.UserDAO;
import com.java.dto.User;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//accepting inputted parameters
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String skillset="";
		//to avoid null skills selected
		if(request.getParameterValues("skills")!=null) {
			List<String> skillList = new ArrayList<String>();
			for(String skill:request.getParameterValues("skills")) {
				skillList.add(skill);
			}
			skillset = skillList.toString();
		}
		String city = request.getParameter("city");
		String gender = request.getParameter("gender");
		
		User newUser = UserDAO.CreateUser(firstname, lastname, password, gender, skillset, city);
		boolean registered = UserDAO.RegisterUser(newUser);
		PrintWriter pw = response.getWriter();
		if(registered) {
			request.getRequestDispatcher("./ShowDetail").forward(request, response);
		}else {
			pw.println("Registration Failed");
		}
	}
	
	


	
	
	
	
	
	
	
	
	
	//optional doPost controller
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
