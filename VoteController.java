package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.java.dto.Food;
//import com.java.dto.User;

@WebServlet("/vote")
public class VoteController extends HttpServlet {
	
	public static Map<String, Integer> votes;
	public static List<String> userips;

	//
//	public static Set<Food> foods = new TreeSet<Food>();
//	public static Set<User> users = new TreeSet<User>();
	
	static {
		votes = new HashMap<String, Integer>();
		userips = new ArrayList<String>();
		votes.put("foodA", 0);
		votes.put("foodB", 0);
		votes.put("foodC", 0);
		votes.put("foodD", 0);
		//why does this not work???
//		foods.add(new Food("foodA",0));
//		foods.add(new Food("foodB",0));
//		foods.add(new Food("foodC",0));
//		foods.add(new Food("foodD",0));
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		//writer.println("please vote");
		// why like this?
		writer.println("<html><body><form action='./vote' method='post'>");
		writer.println("<input type='checkbox' name='menu' value = 'foodA'/>i like food A <br/>");
		writer.println("<input type='checkbox' name='menu' value = 'foodB'/>i like food B <br/>");
		writer.println("<input type='checkbox' name='menu' value = 'foodC'/>i like food C <br/>");
		writer.println("<input type='checkbox' name='menu' value = 'foodD'/>i like food D <br/>");
		writer.println("<input type='submit'/></form></body></html>");
		writer.close();
		writer.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String userip = req.getRemoteAddr();
		//userip already recorded
		if( userips.contains(userip) ) {
			writer.println("You have voted before. Thank you.");
			for( String str: votes.keySet() ) {
				writer.println("str: " + votes.get(str));
			}		
		}
		// new user here to vote
		else {
			for(String str: req.getParameterValues("menu")) {
				votes.put(str, votes.get(str) + 1);
			}
			writer.println("Vote success! Thank you for your vote");
			for( String str: votes.keySet() ) {
				writer.println(str + ": " + votes.get(str));
			}
			userips.add(userip);
		}
		writer.close();
	}
}