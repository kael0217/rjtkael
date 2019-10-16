package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class BillController
 */
@WebServlet("/bill")
public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int billId=99;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		billId++;
//		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		
		int consumerNum =Integer.parseInt(request.getParameter("consumer"));
		float lastMonth = Float.parseFloat(request.getParameter("lastmonth"));
		float thisMonth = Float.parseFloat(request.getParameter("thismonth"));
		
		float units = thisMonth-lastMonth;
		float netAmount = units*1.15f+100f;

//		session.setAttribute("consumerNum", consumerNum);
//		session.setAttribute(name, value);
		Connection conn1 = null;
		boolean checked=false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn1 = DriverManager.getConnection("jdbc:oracle:thin:@kaelPC.local:1521:kaeldb","SYSTEM","Manager");
			PreparedStatement pStatement = conn1.prepareStatement("insert into BillDetails values(?,?,?,?,?,?)");
			pStatement.setInt(1, billId);
			pStatement.setInt(2, consumerNum);
			pStatement.setFloat(3, thisMonth);
			pStatement.setFloat(4, units);
			pStatement.setFloat(5, netAmount);
			pStatement.setDate(6, Date.valueOf(LocalDate.now()));
			pStatement.execute();
			pStatement.close();
			checked = true;
		} catch (SQLException e) {
			if(!checked)
				System.out.println("Insert record fail!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		pw.println("<html><body>");
		pw.println("Electricity Bill for Consumer Number - "+ consumerNum+ " is <br/>");
		pw.println("<br/>");
		pw.println("Unit Consumed:: "+ units+ "<br/>");
		pw.println("Net Amount:: Rs."+ netAmount+"<br/>"); 
		pw.println("</body></html>");
		
		pw.close();
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
