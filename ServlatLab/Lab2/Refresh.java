package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Refresh
 */
@WebServlet("/Refresh")
public class Refresh extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static int refreshtime=0;
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	refreshtime++;
        response.setIntHeader("Refresh", 5);
        
        PrintWriter pw = response.getWriter();
        pw.println("this is the " + refreshtime+ " time refresh");


    }

}