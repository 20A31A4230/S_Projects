package com.uniquedeveloper.registration;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		Connection con = null;
		RequestDispatcher dispatcher = null;
		
//		
//		PrintWriter out = response.getWriter();
//		out.print(uname);
//		out.print(uemail);
//		out.print(upwd);
//		out.print(umobile);
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube?useSSL=false","root","W@2915djkq#");
			PreparedStatement pstmt = con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)");
			pstmt.setString(1, uname);
			pstmt.setString(2, upwd);
			pstmt.setString(3, uemail);
			pstmt.setString(4, umobile);
			
			int rowCount = pstmt.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if (rowCount>0) {
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status","failed");
			}
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
