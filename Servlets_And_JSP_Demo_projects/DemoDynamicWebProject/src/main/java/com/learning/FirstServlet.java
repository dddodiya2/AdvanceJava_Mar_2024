package com.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	//doGet
	//doPost
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) 	throws ServletException {
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		System.out.println("FullName is : " + firstName + lastName);
	}
	
}
