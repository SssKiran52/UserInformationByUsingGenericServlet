package com.jsp.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/LoginCheck")
public class LoginCheck extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");
		
		
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = connection.prepareStatement("select * from userinformation where User_EmailId=? and User_Password=?");
			ps.setString(1, emailId);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			if (resultSet.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("Welcome.html");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("UserLogin.html");
				rd.include(request, response);
				writer.println("<center><h1>Invalid Credentials</h1></center>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
