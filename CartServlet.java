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

@WebServlet("/remove")
public class CartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		HttpSession hsession = req.getSession();
		List<String> cart = (List<String>) hsession.getAttribute("cart");
		
		for(String str: req.getParameterValues("remove")) {
			cart.remove(str);
		}
		writer.println("<a href=\"http://localhost:8080/day15-java-practice2/bookStore.html\">Back to Main page</a>");
		writer.println("items removed");
		hsession.setAttribute("cart", cart);
		if( cart.size() != 0 ) {
			writer.println("current items in cart: ");
			for( String item: cart ) {
				writer.println(item );
			}
		}else {
			writer.println("Your cart is empty.");
		}

	}
}
