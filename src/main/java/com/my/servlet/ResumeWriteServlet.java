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

/**
 * Servlet implementation class ResumeWriteServlet
 */
@WebServlet("/resumewrite")
public class ResumeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");//ISO_88859_1
		PrintWriter out=response.getWriter();//응답출력스트림 얻기
		HttpSession session=request.getSession();
		
		String title=request.getParameter("title");
		String age=request.getParameter("age");
		String introduce=request.getParameter("introduce");
		System.out.println(title);
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result=null;
		
		try {
			con=MyConnection.getConnection();
			String insertResumeSQL="INSERT INTO RESUME_TB(resume_no, resume_title, resume_age, resume_introduce) VALUES(resume_no.NEXTVAL,?,?,?)";
			pstmt=con.prepareStatement(insertResumeSQL);
			//회원번호를 어떻게 받을까,,, 로그인 세션을 통해서 받아야할 것 같은데... 
			pstmt.setString(1, title);
			pstmt.setString(2, age);
			pstmt.setString(3, introduce);
			pstmt.executeUpdate();
			result="{\"status\":1}";
			out.print(result);
		} catch (SQLException e) {
			result="{\"status\":0}";
			out.print(result);
			e.printStackTrace();
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

