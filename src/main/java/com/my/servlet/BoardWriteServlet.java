package com.my.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.sql.MyConnection;


@WebServlet("/boardwrite")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");//ISO_88859_1
		PrintWriter out=response.getWriter();//응답출력스트림 얻기
		HttpSession session=request.getSession();
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result=null;
		
		try {
			con=MyConnection.getConnection();
			String insertBoardSQL="INSERT INTO board_tb(b_no,user_no,b_title,b_content,b_writeday) VALUES(b_no.NEXTVAL,?,?,?,SYSDATE)";
			pstmt=con.prepareStatement(insertBoardSQL);
			pstmt.setInt(1, 1);//회원번호를 어떻게 받을까,,, 로그인 세션을 통해서 받아야할 것 같은데... 
			pstmt.setString(2, title);
			pstmt.setString(3, content);
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

