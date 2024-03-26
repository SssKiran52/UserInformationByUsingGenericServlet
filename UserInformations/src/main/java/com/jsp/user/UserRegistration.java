package com.jsp.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserRegistration")
public class UserRegistration extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String emailid = request.getParameter("emailid");
		String password = request.getParameter("password");
		String mobilenumber = request.getParameter("mb");
		String address = request.getParameter("add");
		
//		System.out.println(firstname);
//		System.out.println(lastname);
//		System.out.println(emailid);
//		System.out.println(password);
//		System.out.println(mobilenumber);
//		System.out.println(address);
		
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = connection.prepareStatement("insert into userinformation(User_FirstName, User_LastName, User_EmailId, User_Password, User_MobileNumber, User_Address) values(?,?,?,?,?,?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, emailid);
			ps.setString(4, password);
			ps.setString(5, mobilenumber);
			ps.setString(6, address);
			int result = ps.executeUpdate();
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			if (result!=0) {
				RequestDispatcher rd = request.getRequestDispatcher("UserLogin.html");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("UserRegistration.html");
				rd.include(request, response);
				writer.println("<center><h1>Invalid Details</h1></center>");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
