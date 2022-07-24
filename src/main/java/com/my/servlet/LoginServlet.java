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
import javax.servlet.http.HttpSession;

import com.my.sql.MyConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청전달데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DB와 연결
		Connection con = null;
		//SQL송신
		PreparedStatement pstmt = null;
		//송신결과
		ResultSet rs = null;
		//응답결과
		String result = "{\"status\": 0}";
		
		//세션(클라이언트별 객체)얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		try {
			con = MyConnection.getConnection();
			String selectIdNPwdSQL = "SELECT * FROM customer_tb WHERE user_id=? AND user_pwd=?";
			pstmt = con.prepareStatement(selectIdNPwdSQL);
			pstmt.setString(1,  id);
			pstmt.setString(2,  pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) { //로그인 성공인 경우
				result = "{\"status\": 1}";
				session.setAttribute("loginInfo", "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB와의 연결 닫기
			MyConnection.close(rs,  pstmt, con);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
//		if("id1".equals(id) && "p1".equals(pwd)) {
//			out.print("{\"status\": 1}");
//		}else {
//			out.print("{\"status\": 2}");
//		}
		out.print(result);
		
	}

}