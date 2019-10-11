package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/shop")
public class BookServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		HttpSession ses = req.getSession();
		List<String> cart = (List<String>) ses.getAttribute("cart");
		pw.println("<html><body><a href=\"http://localhost:8080/day15-java-practice2/bookStore.html\">main</a><br>");
		if( cart == null || cart.size() == 0  ) {
			pw.println("Your cart is empty");
		}else {
			pw.println("<form action='./remove' method='post'>");
			int i = 0;
			pw.println("You have ");
			for( String str: cart )
				pw.println("<br>"+str+"<input type='checkbox' name='remove' value="+str+">");
			pw.println("<br>Please check ones you want to delete");
			pw.println("<input type='submit' value='remove'/></form>");
		}
		pw.println("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		HttpSession ses = req.getSession();
		if( req.getParameter("click").equalsIgnoreCase("addcart")) {
			List<String> cart = (List<String>) ses.getAttribute("cart");
			if( cart == null ) {
				ArrayList<String> car = new ArrayList<>();
				for(String str: req.getParameterValues("booklist")) {
					car.add(str);
				}
				ses.setAttribute("cart", car);
			}else {
				List<String> car = (List<String>) ses.getAttribute("cart");
				for(String str: req.getParameterValues("booklist")) {
					car.add(str);
				}
				ses.setAttribute("cart", car);
			}
			pw.println("You have ");
			cart = (List<String>) ses.getAttribute("cart");
			for( String str: cart ) {
				pw.println( str);
			}
			pw.println("in your cart");
		}else {
			ses.setAttribute("cart", null);
			ses.setAttribute("username", null);
			pw.println("See you next time!");
		}
	}
}
