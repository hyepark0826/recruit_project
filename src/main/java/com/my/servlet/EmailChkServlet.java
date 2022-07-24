package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Customer;
import com.my.sql.MyConnection;

/**
 * Servlet implementation class IdChkServlet
 */
@WebServlet("/emailchk")
public class EmailChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmailChkServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String result = "{\"status\":0, \"msg\": \"이미 사용중인 이메일입니다\"}";
		
		//DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectEmailChkSQL = "SELECT * FROM customer_tb WHERE user_email = ?";
		try {
		    con = MyConnection.getConnection();
		    pstmt = con.prepareStatement(selectEmailChkSQL);
		    pstmt.setString(1, email);
		    rs = pstmt.executeQuery();
		    if(!rs.next()) {
		    	result = "{\"status\":1, \"msg\": \"사용가능한 이메일입니다\"}";
		    }	
		} catch(SQLException e) {
			e.printStackTrace();
			String msg = e.getMessage();
			result = "{\"status\":0, \"+ msg\"" + msg +"\"}";	
		} finally {
			MyConnection.close(rs,  pstmt, con);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
